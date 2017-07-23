package monitor.rest;

import monitor.statistics.Statistics;
import monitor.statistics.Statistics.LivenessQuota;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@XmlRootElement(name = "statistics")
@XmlType(propOrder = { "totalQuota", "quotas" })
public class StatisticsXml {

	private QuotaXml totalQuota;
	private QuotasXml quotas;

	private StatisticsXml() { }

	public static StatisticsXml from(Statistics stats) {
		StatisticsXml xml = new StatisticsXml();
		xml.setTotalQuota(QuotaXml.from("total", stats.totalLivenessQuota()));
		xml.setQuotas(QuotasXml.from(stats.livenessQuotaByService()));
		return xml;
	}

	@XmlElement(name = "total")
	public QuotaXml getTotalQuota() {
		return totalQuota;
	}

	public void setTotalQuota(QuotaXml totalQuota) {
		this.totalQuota = totalQuota;
	}

	@XmlElement(name = "services")
	public QuotasXml getQuotas() {
		return quotas;
	}

	public void setQuotas(QuotasXml quotas) {
		this.quotas = quotas;
	}

	@XmlRootElement(name = "services")
	public static class QuotasXml {

		private List<QuotaXml> serviceQuotas;

		private QuotasXml() { }

		public static QuotasXml from(Stream<Entry<String, LivenessQuota>> quotas) {
			QuotasXml xml = new QuotasXml();
			xml.setServiceQuotas(quotas
				.map(serviceQuota -> QuotaXml.from(serviceQuota.getKey(), serviceQuota.getValue()))
				.collect(toList()));
			return xml;
		}

		@XmlElement(name = "service")
		public List<QuotaXml> getServiceQuotas() {
			return serviceQuotas;
		}

		public void setServiceQuotas(List<QuotaXml> serviceQuotas) {
			this.serviceQuotas = serviceQuotas;
		}

	}

	@XmlRootElement(name = "quota")
	public static class QuotaXml {

		private String serviceName;
		private long dataPointCount;
		private long aliveCount;

		private QuotaXml() {
		}

		public static QuotaXml from(String name, LivenessQuota quota) {
			QuotaXml xml = new QuotaXml();
			xml.setServiceName(name);
			xml.setDataPointCount(quota.dataPointCount());
			long count = Math.round(quota.dataPointCount() * quota.livenessQuota());
			xml.setAliveCount(count);
			return xml;
		}

		@XmlElement(name = "name")
		public String getServiceName() {
			return serviceName;
		}

		public void setServiceName(String serviceName) {
			this.serviceName = serviceName;
		}

		@XmlElement(name = "dataPoints")
		public long getDataPointCount() {
			return dataPointCount;
		}

		public void setDataPointCount(long dataPointCount) {
			this.dataPointCount = dataPointCount;
		}

		@XmlElement(name = "aliveCount")
		public long getAliveCount() {
			return aliveCount;
		}

		public void setAliveCount(long aliveCount) {
			this.aliveCount = aliveCount;
		}

	}

}
