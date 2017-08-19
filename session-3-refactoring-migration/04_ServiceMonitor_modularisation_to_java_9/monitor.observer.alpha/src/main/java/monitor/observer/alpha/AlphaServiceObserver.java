package monitor.observer.alpha;

import monitor.observer.DiagnosticDataPoint;
import monitor.observer.ServiceObserver;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.Random;

public class AlphaServiceObserver implements ServiceObserver {

	private static final Random RANDOM = new Random();

	private final String serviceName;

	private AlphaServiceObserver(String serviceName) {
		this.serviceName = serviceName;
	}

	public static Optional<ServiceObserver> createIfAlphaService(String service) {
		return Optional.of(service)
				// this check should do something more sensible
				.filter(s -> s.contains("alpha"))
				.map(AlphaServiceObserver::new);
	}

	@Override
	public DiagnosticDataPoint gatherDataFromService() {
		// this check should actually contact the serviceName
		boolean alive = RANDOM.nextFloat() > 0.25;
		return DiagnosticDataPoint.of(serviceName, ZonedDateTime.now(), alive);
	}

}
