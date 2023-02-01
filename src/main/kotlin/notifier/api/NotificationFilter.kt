package notifier.api

fun interface NotificationFilter<T> {
    fun shouldNotify(prev: T?, new: T): Boolean
}
