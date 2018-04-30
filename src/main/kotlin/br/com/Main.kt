package br.com

import br.com.metrics.BugCreatedMetric
import br.com.metrics.BugThroughputMetric
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

        throughtput(trelloService, args)
        cfd(trelloService, args)
    }

    fun throughtput(trelloService: TrelloServiceClient, args: Array<String>) {
        val bugMetric: Metric = BugCreatedMetric()
        val cardCountMetric: Metric = CardsCountMetric(trelloService)
        val bugThroughputMetric: Metric = BugThroughputMetric(trelloService)

        var result = args[7] + " - " + args[9] + ";"
        val totalCards = cardCountMetric.calculate(args)
        val bugThroughput = bugThroughputMetric.calculate(args)
        result += (totalCards - bugThroughput).toString() + ";"
        result += bugThroughput.toString() + ";"
        result += totalCards.toString() + ";"
        result += bugMetric.calculate(args).toString()

        val strategy: PrinterStrategy? = strategies.find { it.type() == 1}

        println("Throughput:")
        println("Semana; Throughput(US); Throughput Bugs; Throughput; Novos Bugs")
        strategy!!.print(result)
    }

    fun cfd(trelloService: TrelloServiceClient, args: Array<String>) {
        val lists = trelloService.getListsFromBoard(args[5])
        var result = args[7] + " - " + args[9] + ";"
        lists.map {
            for (list in it) {
                if(list.name != "Release Plan" && list.name != "Design" && list.name != "Bloqueado"){
                    result += trelloService.getCardsFromList(list.id).get().count().toString() + ";"
                }
            }
        }

        val strategy: PrinterStrategy? = strategies.find { it.type() == 1 }

        println("CFD:")
        println("Semana; Backlog; Backlog Semanal; Doing; Review; Done")
        strategy!!.print(result)
    }
}