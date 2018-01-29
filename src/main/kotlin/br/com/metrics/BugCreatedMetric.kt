package br.com.metrics

import br.com.trello.Card
import br.com.trello.Label
import br.com.trello.TrelloServiceClient
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class BugCreatedMetric : Metric{

    private val BUG_LABEL = "BUG"

    override fun calculate(args: Array<String>): Int {
        val cards = TrelloServiceClient(args[1], args[3]).getBoardInfo(args[5])

        if(!cards.isPresent()) return 0

        return countWeeklyBugs(cards.get(), args[7], args[9])
    }

    private fun countWeeklyBugs(cards: List<Card>, initialDate: String, finalDate: String): Int {
        val bugsCard = cards.filter { it.name.contains(BUG_LABEL) || hasBugLabel(it) }
        val weekBugsCards = getWeeklyBugs(bugsCard, initialDate, finalDate)
        return weekBugsCards.count()
    }

    private fun getWeeklyBugs(bugsCard: List<Card>, initialDate: String, finalDate: String): List<Card> {
        val dateIni = LocalDate.parse(initialDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        val dateFinal = LocalDate.parse(finalDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"))

        return bugsCard.filter {
            val epoch = it.id.take(8).toLong(radix = 16)
            val netDate = Instant.ofEpochMilli(epoch * 1000L).atZone(ZoneId.systemDefault()).toLocalDate();

            netDate >= dateIni && netDate <= dateFinal
        }
    }

    private fun hasBugLabel(it: Card): Boolean {
       return it.labels.any { label ->
            label.name.contains(BUG_LABEL)
        }
    }
}