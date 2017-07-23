package monitor.rest;

import monitor.statistics.Statistics;
import monitor.statistics.Statistics.LivenessQuota;
import spark.Spark;

import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class MonitorServer {

	private final Supplier<Statistics> statistics;

	private MonitorServer(Supplier<Statistics> statistics) {
		this.statistics = requireNonNull(statistics);
	}

	public static MonitorServer create(Supplier<Statistics> statistics) {
		return new MonitorServer(statistics);
	}

	public MonitorServer start() {
		Spark.get("/statistics", (req, res) -> getStatisticsAsJson());
		return this;
	}

	private String getStatisticsAsJson() {
		return toJson(statistics.get());
	}

	private static String toJson(Statistics stats) {
		String totalQuota = format("{ \"total\": %s }", toJson(stats.totalLivenessQuota()));
		String serviceQuotas = stats.livenessQuotaByService()
				.map(quota -> format("{ \"%s\": %s }", quota.getKey(), toJson(quota.getValue())))
				.collect(Collectors.joining(", "));
		return format("{ \"liveness\": [ %s, %s ] }%n", totalQuota, serviceQuotas);
	}

	private static String toJson(LivenessQuota quota) {
		return format("{ \"quota\": %s, \"data points\": %d }", quota.livenessQuota(), quota.dataPointCount());
	}

	public MonitorServer shutdown() {
		Spark.stop();
		return this;
	}

}
