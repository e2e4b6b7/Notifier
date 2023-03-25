package notifier.api

interface Logger<in T> {
    fun init() = Unit
    fun onBodyReceived(body: String) = Unit
    fun onBodyChanged(prev: String?, new: String) = Unit
    fun onDataChanged(prev: T?, new: T) = Unit
    fun onError(e: Exception) = Unit
}
