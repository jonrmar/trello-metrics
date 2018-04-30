package br.com.metrics

import br.com.trello.Card
import br.com.trello.TrelloList
import br.com.trello.TrelloServiceClient
import java.util.*

class BugThroughputMetric : Metric {

    private val BUG_LABEL = "BUG"
    var trelloServiceClient: TrelloServiceClient

    constructor(trelloServiceClient: TrelloServiceClient) {
        this.trelloServiceClient = trelloServiceClient
    }

    override fun calculate(args: Array<String>): Int {
        val lists = trelloServiceClient.getListsFromBoard(args[5])

        if (!lists.isPresent()) return 0

        return countBugsThroughput(lists, args)
    }

    private fun countBugsThroughput(lists: Optional<List<TrelloList>>, args: Array<String>): Int {
        val list = lists.get().find { it.name == args[11] }
        val cards = trelloServiceClient.getCardsFromList(list!!.id)

        return if (!cards.isPresent()) 0
        else cards.get().filter { card: Card -> card.name == BUG_LABEL || hasBugLabel(card) }.count()
    }

    private fun hasBugLabel(it: Card): Boolean {
        return it.labels.any { label ->
            label.name.contains(BUG_LABEL)
        }
    }
}