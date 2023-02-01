package notifier.api

interface SiteParser<T> {
    val url: String
    fun parse(body: String): T
}
