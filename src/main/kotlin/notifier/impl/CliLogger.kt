package notifier.impl

import notifier.api.Logger
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CliLogger : Logger<Any> {
    private val dtf: DateTimeFormatter = DateTimeFormatter.ofPattern("dd HH:mm:ss")

    override fun onDataChanged(prev: Any?, new: Any) {
        println("${dtf.format(LocalDateTime.now())}: New best time: $new")
    }

    override fun onError(e: Exception) {
        println(e.message)
    }
}
