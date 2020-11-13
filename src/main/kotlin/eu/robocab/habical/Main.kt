package eu.robocab.habical

import org.http4k.core.Method.GET
import org.http4k.core.Request

const val HABITICA_BASE_URL = "https://habitica.com/api/v3"
const val APP_NAME = "habical"
const val CLIENT_HEADER = "X-Client"
const val API_USER_HEADER = "X-Api-User"
const val API_KEY_HEADER = "X-Api-Key"
const val MAINTAINER_ID_ENV = "HABITICA_MAINTAINER_ID"
const val USER_ID_ENV = "HABITICA_USER_ID"
const val API_TOKEN_ENV = "HABITICA_API_TOKEN"

fun main() {
    println("habical")
    val maintainerId = getFromEnv(MAINTAINER_ID_ENV)
    val userId = getFromEnv(USER_ID_ENV)
    val apiToken = getFromEnv(API_TOKEN_ENV)

    val client = HabiticaClient(apiToken, userId, "$maintainerId-$APP_NAME")
    val response = client(Request(GET, "$HABITICA_BASE_URL/user"))

    println(response.bodyString())
}

private fun getFromEnv(envVarName: String) = System.getenv(envVarName)
    ?: error("No $envVarName configured.")
