package monitor.observer;

import java.time.ZonedDateTime;
import java.util.Objects;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public final class DiagnosticDataPoint {

	private final String service;
	private final ZonedDateTime timestamp;
	private final boolean alive;

	private DiagnosticDataPoint(String service, ZonedDateTime timestamp, boolean alive) {
		this.service = requireNonNull(service);
		this.timestamp = requireNonNull(timestamp);
		this.alive = alive;
	}

	public static DiagnosticDataPoint of(String service, ZonedDateTime timestamp, boolean alive) {
		return new DiagnosticDataPoint(service, timestamp, alive);
	}

	public String service() {
		return service;
	}

	public ZonedDateTime timestamp() {
		return timestamp;
	}

	public boolean alive() {
		return alive;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		DiagnosticDataPoint that = (DiagnosticDataPoint) o;
		return alive == that.alive
				&& Objects.equals(service, that.service)
				&& Objects.equals(timestamp, that.timestamp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(service, timestamp, alive);
	}

	@Override
	public String toString() {
		return format("DiagnosticDataPoint{%s / %s: alive=%s}", service, timestamp, alive);
	}
}
