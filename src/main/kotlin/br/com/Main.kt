package br.com

import br.com.metrics.Metric
import br.com.metrics.MetricFactory
import br.com.printout.PrinterStrategy
import br.com.printout.strategies.ConsolePrinter

object Main {

    val strategies: List<PrinterStrategy> = listOf(ConsolePrinter())

    @JvmStatic
    fun main(args : Array<String>){
        val metricFactory = MetricFactory()

        val metric: Metric = metricFactory.getMetric("1")

        val strategy: PrinterStrategy? = strategies.find { it.type() == 1}
        strategy?.print(metric.calculate(args).toString())
    }
}