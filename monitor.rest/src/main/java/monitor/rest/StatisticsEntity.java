package monitor.rest;

import monitor.statistics.Statistics;
import monitor.statistics.Statistics.LivenessQuota;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Generated("NIPA-TYPING")
@XmlRootElement(name = "statistics")
@XmlType(propOrder = { "totalQuota", "quotas" })
public class StatisticsEntity {

	private QuotaEntity totalQuota;
	private QuotasEntity quotas;

	private StatisticsEntity() { }

	public static StatisticsEntity from(Statistics stats) {
		StatisticsEntity entity = new StatisticsEntity();
		entity.setTotalQuota(QuotaEntity.from("total", stats.totalLivenessQuota()));
		entity.setQuotas(QuotasEntity.from(stats.livenessQuotaByService()));
		return entity;
	}

	@XmlElement(name = "total")
	public QuotaEntity getTotalQuota() {
		return totalQuota;
	}

	public void setTotalQuota(QuotaEntity totalQuota) {
		this.totalQuota = totalQuota;
	}

	@XmlElement(name = "services")
	public QuotasEntity getQuotas() {
		return quotas;
	}

	public void setQuotas(QuotasEntity quotas) {
		this.quotas = quotas;
	}

	@XmlRootElement(name = "services")
	public static class QuotasEntity {

		private List<QuotaEntity> serviceQuotas;

		private QuotasEntity() { }

		public static QuotasEntity from(Stream<Entry<String, LivenessQuota>> quotas) {
			QuotasEntity entity = new QuotasEntity();
			entity.setServiceQuotas(quotas
				.map(serviceQuota -> QuotaEntity.from(serviceQuota.getKey(), serviceQuota.getValue()))
				.collect(toList()));
			return entity;
		}

		@XmlElement(name = "service")
		public List<QuotaEntity> getServiceQuotas() {
			return serviceQuotas;
		}

		public void setServiceQuotas(List<QuotaEntity> serviceQuotas) {
			this.serviceQuotas = serviceQuotas;
		}

	}

	@XmlRootElement(name = "quota")
	public static class QuotaEntity {

		private String serviceName;
		private long dataPointCount;
		private long aliveCount;

		private QuotaEntity() {
		}

		public static QuotaEntity from(String name, LivenessQuota quota) {
			QuotaEntity entity = new QuotaEntity();
			entity.setServiceName(name);
			entity.setDataPointCount(quota.dataPointCount());
			long count = Math.round(quota.dataPointCount() * quota.livenessQuota());
			entity.setAliveCount(count);
			return entity;
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
