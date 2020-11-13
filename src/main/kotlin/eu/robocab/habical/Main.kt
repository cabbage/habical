package eu.robocab.habical

import org.http4k.client.OkHttp
import org.http4k.core.Method.GET
import org.http4k.core.Request

const val HABITICA_BASE_URL = "https://habitica.com/api/v3"
const val APP_NAME = "habical"
const val CLIENT_HEADER = "X-Client"
const val API_USER_HEADER = "X-Api-User"
const val API_KEY_HEADER = "X-Api-Key"
const val MAINTAINER_USER_ID_ENV = "HABITICA_MAINTAINER_USER_ID"
const val USER_ID_ENV = "HABITICA_USER_ID"
const val API_TOKEN_ENV = "HABITICA_API_TOKEN"

fun main() {
    println("habical")
    val maintainerUserId = getFromEnv(MAINTAINER_USER_ID_ENV)
    val userId = getFromEnv(USER_ID_ENV)
    val apiToken = getFromEnv(API_TOKEN_ENV)


    val client = OkHttp()
    val response = client(Request(GET, "$HABITICA_BASE_URL/user")
        .headers(listOf(
            CLIENT_HEADER to "$maintainerUserId-$APP_NAME",
            API_USER_HEADER to userId,
            API_KEY_HEADER to apiToken,
        )))

    println(response.bodyString())
}

private fun getFromEnv(envVarName: String) = System.getenv(envVarName)
    ?: error("No $envVarName configured.")
