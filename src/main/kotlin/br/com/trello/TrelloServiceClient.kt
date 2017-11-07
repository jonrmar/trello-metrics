package br.com.trello

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.web.client.RestTemplate
import java.util.*

class TrelloServiceClient(var restClient: RestTemplate,
                          var apiKey: String,
                          var token: String) {

     fun getUserInfo(): Optional<User>{
        try {
            val response = restClient.getForEntity("https://api.trello.com/1/members/me?fields=all&key=${apiKey}&token=${token}", User::class.java)
            return Optional.of(response.body)
        }
        catch (ex: org.jsoup.HttpStatusException){
            return Optional.empty<User>()
        }
    }

}

@JsonIgnoreProperties(ignoreUnknown = true)
data class User constructor (var fullName: String, var idBoards: Array<String>)