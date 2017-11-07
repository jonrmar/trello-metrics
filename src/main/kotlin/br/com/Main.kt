package br.com

import br.com.trello.TrelloServiceClient
import org.springframework.web.client.RestTemplate

object Main {

    @JvmStatic
    fun main(args : Array<String>){
        val userInfo = br.com.trello.TrelloServiceClient(RestTemplate(), args[1], args[3]).getUserInfo()
        println(userInfo)
    }
}