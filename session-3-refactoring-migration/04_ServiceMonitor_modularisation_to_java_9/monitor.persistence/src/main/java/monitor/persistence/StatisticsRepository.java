package monitor.persistence;

import monitor.statistics.Statistics;

import java.util.Comparator;
import java.util.Map.Entry;
import java.util.Optional;

public class StatisticsRepository {

	public Optional<Statistics> load() {
		return Optional.empty();
	}

	public void store(Statistics statistics) {
		System.out.printf("Total liveness: %s (from %d data points)%n",
				statistics.totalLivenessQuota().livenessQuota(),
				statistics.totalLivenessQuota().dataPointCount());
		statistics.livenessQuotaByService()
				.sorted(Comparator.comparing(Entry::getKey))
				.forEach(serviceLiveness ->
				System.out.printf(" * %s liveness: %s (from %d data points)%n",
						serviceLiveness.getKey(),
						serviceLiveness.getValue().livenessQuota(),
						serviceLiveness.getValue().dataPointCount()));
		System.out.println();
	}

}
