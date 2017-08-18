package monitor.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import monitor.statistics.Statistics;
import spark.Spark;

import javax.annotation.Nonnull;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;
import static monitor.utils.Utils.toBase64;

public class MonitorServer {

	private final Supplier<Statistics> statistics;

	private MonitorServer(Supplier<Statistics> statistics) {
		this.statistics = requireNonNull(statistics);
	}

	@Nonnull
	public static MonitorServer create(@Nonnull Supplier<Statistics> statistics) {
		return new MonitorServer(statistics);
	}

	@Nonnull
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
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(StatisticsEntity.from(stats));
		} catch (JsonProcessingException ex) {
			// don't do this in real live
			return ex.toString();
		}
	}

	private String getStatisticsAsXml() {
		return toXml(statistics.get());
	}

	private static String toXml(Statistics stats) {
		try {
			JAXBContext context = JAXBContext.newInstance(StatisticsEntity.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			StringWriter writer = new StringWriter();
			marshaller.marshal(StatisticsEntity.from(stats), writer);
			return writer.toString();
		} catch (JAXBException ex) {
			// don't do this in real live
			return ex.toString();
		}
	}

	@Nonnull
	public MonitorServer shutdown() {
		Spark.stop();
		return this;
	}

}
