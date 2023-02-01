package notifier

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.runBlocking
import notifier.api.Logger
import notifier.api.NotificationFilter
import notifier.api.Notifier
import notifier.api.SiteParser

class Executor<T>(
    private val parser: SiteParser<T>,
    private val filter: NotificationFilter<T>,
    private val notifier: Notifier<T>,
    private val cooldown: Long,
    private val logger: Logger<T>,
) {
    private val client = HttpClient()

    fun run() {
        var prevBody: String? = null
        var prevData: T? = null

        while (true) {
            try {
                val body = runBlocking { client.get(parser.url).bodyAsText() }
                if (body == prevBody) continue
                logger.onBodyChanged(prevBody, body)
                prevBody = body

                val data = parser.parse(body)
                if (data == prevData) continue
                logger.onDataChanged(prevData, data)
                if (filter.shouldNotify(prevData, data))
                    notifier.notify(prevData, data)
                prevData = data
            } catch (e: Exception) {
                logger.onError(e)
            }
            Thread.sleep(cooldown)
        }
    }
}
