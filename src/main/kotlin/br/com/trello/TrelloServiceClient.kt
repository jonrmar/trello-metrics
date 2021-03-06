package br.com.trello

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.RequestEntity
import org.springframework.web.client.RestTemplate
import java.net.URI
import java.util.*

class TrelloServiceClient(private var apiKey: String,
                          private var token: String) {

    private val restClient: RestTemplate =  RestTemplate()
    private val trelloUrl : String = "https://api.trello.com/1"

    fun getUserInfo(): Optional<User>{
        try {
            val response = restClient.getForEntity("${trelloUrl}/members/me?fields=all&key=${apiKey}&token=${token}", User::class.java)
            return Optional.of(response.body)
        }
        catch (ex: org.jsoup.HttpStatusException){
            return Optional.empty<User>()
        }
    }

    fun getCardActions(cardId: String): Optional<List<CardAction>>{
        try {
            val request = RequestEntity<Any>(HttpMethod.GET, URI.create("${trelloUrl}/cards/${cardId}/actions?fields=all&key=${apiKey}&token=${token}"))
            val respType = object: ParameterizedTypeReference<List<CardAction>>(){}

            val response = restClient.exchange(request, respType)
            return Optional.of(response.body)
        }
        catch (ex: org.jsoup.HttpStatusException){
            return Optional.empty<List<CardAction>>()
        }
    }

    fun getBoardInfo(cardId: String): Optional<List<Card>>{
        try {
            val request = RequestEntity<Any>(HttpMethod.GET, URI.create("${trelloUrl}/boards/${cardId}/cards?fields=all&key=${apiKey}&token=${token}"))
            val respType = object: ParameterizedTypeReference<List<Card>>(){}

            val response = restClient.exchange(request, respType)
            return Optional.of(response.body)
        }
        catch (ex: org.jsoup.HttpStatusException){
            return Optional.empty<List<Card>>()
        }
    }

    fun getListsFromBoard(boardId: String): Optional<List<TrelloList>> {
        try {
            val request = RequestEntity<Any>(HttpMethod.GET, URI.create("${trelloUrl}/boards/${boardId}/lists?fields=all&key=${apiKey}&token=${token}"))
            val respType = object : ParameterizedTypeReference<List<TrelloList>>() {}

            val response = restClient.exchange(request, respType)
            return Optional.of(response.body)
        } catch (ex: org.jsoup.HttpStatusException) {
            return Optional.empty<List<TrelloList>>()
        }
    }

    fun getCardsFromList(listId: String): Optional<List<Card>> {
        try {
            val request = RequestEntity<Any>(HttpMethod.GET, URI.create("${trelloUrl}/lists/${listId}/cards?fields=all&key=${apiKey}&token=${token}"))
            val respType = object : ParameterizedTypeReference<List<Card>>() {}

            val response = restClient.exchange(request, respType)
            return Optional.of(response.body)
        } catch (ex: org.jsoup.HttpStatusException) {
            return Optional.empty<List<Card>>()
        }
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
data class User constructor (var fullName: String, var idBoards: Array<String>)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Card constructor (var id: String, var idList: String, var name: String, var labels: List<Label>)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Label constructor(var name: String)

@JsonIgnoreProperties(ignoreUnknown = true)
data class CardAction constructor (var id: String, var type: String, var date: Date)

@JsonIgnoreProperties(ignoreUnknown = true)
data class TrelloList constructor(var id: String, var name: String)