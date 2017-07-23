package monitor.rest;

import monitor.statistics.Statistics;
import monitor.statistics.Statistics.LivenessQuota;
import spark.Spark;
import sun.misc.BASE64Encoder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
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
		Spark.get("/stats/json", (req, res) -> getStatisticsAsJson());
		Spark.get("/stats/json64", (req, res) -> toBase64(getStatisticsAsJson()));
		Spark.get("/stats/xml", (req, res) -> getStatisticsAsXml());
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

	private String getStatisticsAsXml() {
		return toXml(statistics.get());
	}

	private static String toXml(Statistics stats) {
		try {
			JAXBContext context = JAXBContext.newInstance(StatisticsXml.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			StringWriter writer = new StringWriter();
			marshaller.marshal(StatisticsXml.from(stats), writer);
			return writer.toString();
		} catch (JAXBException ex) {
			// don't do this in real live
			return ex.toString();
		}
	}

	private static String toBase64(String content) {
		return new BASE64Encoder().encode(content.getBytes());
	}

	public MonitorServer shutdown() {
		Spark.stop();
		return this;
	}

}
