package br.com

import br.com.metrics.BugCreatedMetric
import br.com.metrics.CardsCountMetric
import br.com.metrics.Metric
import br.com.printout.PrinterStrategy
import br.com.printout.strategies.ConsolePrinter
import br.com.trello.TrelloServiceClient

object Main {

    val strategies: List<PrinterStrategy> = listOf(ConsolePrinter())

    @JvmStatic
    fun main(args : Array<String>){
        val trelloService = TrelloServiceClient(args[1], args[3])

        val bugMetric: Metric = BugCreatedMetric()
        val cardCountMetric: Metric = CardsCountMetric(trelloService)

        val strategy: PrinterStrategy? = strategies.find { it.type() == 1}
        strategy?.print(bugMetric.calculate(args).toString())
        strategy?.print(cardCountMetric.calculate(args).toString())
    }
}