package monitor;

import monitor.observer.DiagnosticDataPoint;
import monitor.observer.ServiceObserver;
import monitor.persistence.StatisticsRepository;
import monitor.statistics.Statistician;
import monitor.statistics.Statistics;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

class Monitor {

	private final List<ServiceObserver> serviceObservers;
	private final Statistician statistician;
	private final StatisticsRepository repository;

	private Statistics currentStatistics;

	public Monitor(
			List<ServiceObserver> serviceObservers,
			Statistician statistician,
			StatisticsRepository repository,
			Statistics initialStatistics) {
		this.serviceObservers = requireNonNull(serviceObservers);
		this.statistician = requireNonNull(statistician);
		this.repository = requireNonNull(repository);
		this.currentStatistics = requireNonNull(initialStatistics);
	}

	public void updateStatistics() {
		List<DiagnosticDataPoint> newDataPoints = serviceObservers.stream()
				.map(ServiceObserver::gatherDataFromService)
				.collect(toList());
		Statistics newStatistics = statistician.compute(currentStatistics, newDataPoints);
		currentStatistics = newStatistics;
		repository.store(newStatistics);
	}

	public Statistics currentStatistics() {
		return currentStatistics;
	}

}
