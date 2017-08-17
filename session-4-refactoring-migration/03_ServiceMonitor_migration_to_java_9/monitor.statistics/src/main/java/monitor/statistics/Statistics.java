package monitor.statistics;

import monitor.observer.DiagnosticDataPoint;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class Statistics {

	private final LivenessQuota totalLivenessQuota;
	private final Map<String, LivenessQuota> livenessQuotaByService;

	private Statistics(LivenessQuota totalLivenessQuota, Map<String, LivenessQuota> livenessQuotaByService) {
		this.totalLivenessQuota = requireNonNull(totalLivenessQuota);
		this.livenessQuotaByService = requireNonNull(livenessQuotaByService);
	}

	static Statistics create() {
		return new Statistics(LivenessQuota.zero(), new HashMap<>());
	}

	Statistics merge(DiagnosticDataPoint dataPoint) {
		Map<String, LivenessQuota> newStatistics = new HashMap<>(livenessQuotaByService);
		LivenessQuota oldServiceQuota = newStatistics.getOrDefault(dataPoint.service(), LivenessQuota.zero());
		newStatistics.put(dataPoint.service(), oldServiceQuota.merge(dataPoint));

		LivenessQuota newTotalQuota = totalLivenessQuota.merge(dataPoint);

		return new Statistics(newTotalQuota, newStatistics);
	}

	public LivenessQuota totalLivenessQuota() {
		return totalLivenessQuota;
	}

	public Stream<Entry<String, LivenessQuota>> livenessQuotaByService() {
		return livenessQuotaByService.entrySet().stream();
	}

	public static final class LivenessQuota {

		private final long dataPointCount;
		private final long aliveCount;

		LivenessQuota(long dataPointCount, long aliveCount) {
			this.dataPointCount = dataPointCount;
			this.aliveCount = aliveCount;
		}

		static LivenessQuota zero() {
			return new LivenessQuota(0,0);
		}

		private static long aliveAsLong(DiagnosticDataPoint dataPoint) {
			return dataPoint.alive() ? 1 : 0;
		}

		LivenessQuota merge(DiagnosticDataPoint dataPoint) {
			long newDataPointCount = this.dataPointCount + 1;
			long newAliveCount = aliveCount + aliveAsLong(dataPoint);
			return new LivenessQuota(newDataPointCount, newAliveCount);
		}

		public long dataPointCount() {
			return dataPointCount;
		}

		public double livenessQuota() {
			return aliveCount / (double) dataPointCount;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			LivenessQuota that = (LivenessQuota) o;
			return dataPointCount == that.dataPointCount
					&& Double.compare(that.aliveCount, aliveCount) == 0;
		}

		@Override
		public int hashCode() {
			return Objects.hash(dataPointCount, aliveCount);
		}

		@Override
		public String toString() {
			return format("LivenessQuota %s (%d data points)", livenessQuota(), dataPointCount);
		}

	}

}
