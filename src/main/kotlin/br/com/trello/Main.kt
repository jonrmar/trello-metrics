package br.com.trello

import br.com.trello.trello.TrelloServiceClient
import org.springframework.web.client.RestTemplate

object Main {

    @JvmStatic
    fun main(args : Array<String>){
        val userInfo = TrelloServiceClient(RestTemplate(), args[1], args[3]).getUserInfo()
        println(userInfo)
    }
}