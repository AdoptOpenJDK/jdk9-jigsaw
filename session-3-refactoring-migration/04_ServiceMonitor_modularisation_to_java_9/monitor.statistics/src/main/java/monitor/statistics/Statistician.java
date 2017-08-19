package monitor.statistics;

import monitor.observer.DiagnosticDataPoint;

public class Statistician {

	public Statistics emptyStatistics() {
		return Statistics.create();
	}

	public Statistics compute(Statistics currentStats, Iterable<DiagnosticDataPoint> dataPoints) {
		Statistics finalStats = currentStats;
		for (DiagnosticDataPoint dataPoint : dataPoints)
			finalStats = finalStats.merge(dataPoint);
		return finalStats;
	}

}
