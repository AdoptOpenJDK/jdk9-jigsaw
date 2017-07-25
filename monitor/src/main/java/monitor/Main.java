package monitor;

import monitor.observer.ServiceObserver;
import monitor.observer.alpha.AlphaServiceObserver;
import monitor.observer.beta.BetaServiceObserver;
import monitor.persistence.StatisticsRepository;
import monitor.rest.MonitorServer;
import monitor.statistics.Statistician;
import monitor.statistics.Statistics;
import monitor.utils.Utils;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Main {

	public static void main(String[] args) {
//		logClassPathContent();

		Monitor monitor = createMonitor();
		MonitorServer server = MonitorServer
				.create(monitor::currentStatistics)
				.start();

		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		scheduler.scheduleAtFixedRate(monitor::updateStatistics, 1, 1, TimeUnit.SECONDS);
		scheduler.schedule(() -> {
					scheduler.shutdown();
					server.shutdown();
				},
				10,
				TimeUnit.SECONDS);
	}

	private static void logClassPathContent() {
		URLClassLoader classLoader = (URLClassLoader) Main.class.getClassLoader();
		String message = stream(classLoader.getURLs())
				.map(URL::toString)
				.map(url -> "\t" + url)
				.collect(joining("\n", "Class path content:\n", "\n"));
		System.out.println(message);
	}

	private static Monitor createMonitor() {
		List<ServiceObserver> observers = Stream.of("alpha-1", "alpha-2", "alpha-3", "beta-1")
				.map(Main::createObserver)
				.flatMap(Utils::stream)
				.collect(toList());
		Statistician statistician = new Statistician();
		StatisticsRepository repository = new StatisticsRepository();
		Statistics initialStatistics = repository.load().orElseGet(statistician::emptyStatistics);

		return new Monitor(observers, statistician, repository, initialStatistics);
	}

	private static Optional<ServiceObserver> createObserver(String serviceName) {
		return Utils.firstPresent(
				() -> AlphaServiceObserver.createIfAlphaService(serviceName),
				() -> BetaServiceObserver.createIfBetaService(serviceName),
				() -> {
					System.out.printf("No observer for %s found.%n", serviceName);
					return Optional.empty();
				});
	}

}
