package notifier.api

fun interface Notifier<T> {
    fun notify(prev: T?, new: T)
}
