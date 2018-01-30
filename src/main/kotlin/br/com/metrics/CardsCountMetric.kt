package br.com.metrics

import br.com.trello.TrelloServiceClient

class CardsCountMetric : Metric {

    var trelloServiceClient: TrelloServiceClient

    constructor(trelloServiceClient: TrelloServiceClient) {
        this.trelloServiceClient = trelloServiceClient
    }

    override fun calculate(args: Array<String>): Int {
        val lists = trelloServiceClient.getListsFromBoard(args[5])

        if (!lists.isPresent()) return 0

        val list = lists.get().find { it.name == args[11] }
        val cards = trelloServiceClient.getCardsFromList(list!!.id)

        return if(cards.isPresent()) cards.get().size else 0
    }
}