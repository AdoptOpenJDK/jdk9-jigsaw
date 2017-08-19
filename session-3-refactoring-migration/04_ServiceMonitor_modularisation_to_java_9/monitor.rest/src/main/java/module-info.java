module monitor.rest {
	requires java.xml.bind;
	requires java.xml.ws.annotation;

	requires monitor.utils;
	requires monitor.statistics;

	requires spark.core;
	requires jackson.core;
	requires jackson.databind;

	exports monitor.rest;
}
