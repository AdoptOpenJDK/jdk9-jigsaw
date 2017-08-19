package monitor.observer.beta;

import monitor.observer.DiagnosticDataPoint;
import monitor.observer.ServiceObserver;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.Random;

public class BetaServiceObserver implements ServiceObserver {

	private static final Random RANDOM = new Random();

	private final String serviceName;

	private BetaServiceObserver(String serviceName) {
		this.serviceName = serviceName;
	}

	public static Optional<ServiceObserver> createIfBetaService(String service) {
		return Optional.of(service)
				// this check should do something more sensible
				.filter(s -> s.contains("beta"))
				.map(BetaServiceObserver::new);
	}

	@Override
	public DiagnosticDataPoint gatherDataFromService() {
		// this check should actually contact the serviceName
		boolean alive = RANDOM.nextFloat() > 0.1;
		return DiagnosticDataPoint.of(serviceName, ZonedDateTime.now(), alive);
	}

}
