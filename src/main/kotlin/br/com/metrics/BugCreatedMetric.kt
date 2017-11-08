package br.com.metrics

import br.com.trello.Card
import br.com.trello.TrelloServiceClient

class BugCreatedMetric : Metric{

    override fun calculate(args: Array<String>): Int {
        val cards = TrelloServiceClient(args[1], args[3]).getBoardInfo(args[7])

        if(!cards.isPresent()) return 0

        return countBugCards(cards.get())
    }

    private fun countBugCards(cards: List<Card>): Int{
        val cardsFiltered = cards.filter { it.name.contains("BUG") }

        return cardsFiltered.count()
    }
}