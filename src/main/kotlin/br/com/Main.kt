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

        chooseMetricOption()
        val option: String? = readLine()
        val metric: Metric = metricFactory.getMetric(option)

        choosePrintOption()
        val strategy: PrinterStrategy? = strategies.find { it.type() == readLine()?.toInt()}
        strategy?.print(metric.calculate(args).toString())
    }

    private fun chooseMetricOption(){
        println("Type in the console the metric number:")
        println("1- Bug Count")
    }

    private fun choosePrintOption(){
        println("Type in the console the print number:")
        println("1- Console")
    }

}