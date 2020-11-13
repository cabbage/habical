package eu.robocab.habical

import org.http4k.client.DualSyncAsyncHttpHandler
import org.http4k.client.OkHttp
import org.http4k.core.Request
import org.http4k.core.Response

object HabiticaClient {
    @JvmStatic
    @JvmName("create")
    operator fun invoke(apiToken: String, userId: String, appId: String) = run {
        val client = OkHttp()
        val defaultHeaders = compileDefaultHeaders(apiToken, userId, appId)
        object : DualSyncAsyncHttpHandler {
            override fun invoke(req: Request): Response =
                client(req.headers(req.headers + defaultHeaders))

            override fun invoke(req: Request, fn: (Response) -> Unit) =
                client(req.headers(req.headers + defaultHeaders), fn)
        }
    }

    private fun compileDefaultHeaders(apiToken: String, userId: String, appId: String) =
        listOf(
            CLIENT_HEADER to appId,
            API_USER_HEADER to userId,
            API_KEY_HEADER to apiToken,
        )
}
