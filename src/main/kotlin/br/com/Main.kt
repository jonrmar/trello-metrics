package br.com

import br.com.trello.TrelloServiceClient
import org.springframework.web.client.RestTemplate

object Main {

    @JvmStatic
    fun main(args : Array<String>){
        // Exemplos de chamadas para métricas do trello
        val restTemplate = RestTemplate()

        val userInfo = TrelloServiceClient(restTemplate, args[1], args[3]).getUserInfo()
        val cardActions = TrelloServiceClient(restTemplate, args[1], args[3]).getCardActions(args[5])
        val boardInfo = TrelloServiceClient(restTemplate, args[1], args[3]).getBoardInfo(args[7])

        println(userInfo)
        println(cardActions)
        println(boardInfo)
    }
}