package eu.robocab.habical

import eu.robocab.habical.habitica.Task
import kotlinx.serialization.Serializable
import org.http4k.core.Body
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.format.KotlinxSerialization
import org.http4k.format.KotlinxSerialization.auto

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
    val user = client(Request(GET, "$HABITICA_BASE_URL/user")).bodyString()

    val json = KotlinxSerialization
    val tasks = client(Request(GET, "$HABITICA_BASE_URL/tasks/user"))
    println(tasks.bodyString())
    val deserializedTasks = tasksLens(tasks)
    println(deserializedTasks)
}

private val tasksLens = Body.auto<TasksResponse>().toLens()

@Serializable
internal data class TasksResponse(
    val data: List<Task>
)

private fun getFromEnv(envVarName: String) = System.getenv(envVarName)
    ?: error("No $envVarName configured.")
