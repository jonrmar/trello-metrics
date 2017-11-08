package br.com.metrics

class MetricFactory {

    fun getMetric(metricType: String?) = when (metricType) {
        "1" -> BugCreatedMetric()
        else -> BugCreatedMetric()
    }
}