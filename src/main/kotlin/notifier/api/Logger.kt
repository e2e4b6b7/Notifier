package notifier.api

interface Logger<T> {
    fun init() = Unit
    fun onBodyChanged(prev: String?, new: String) = Unit
    fun onDataChanged(prev: T?, new: T) = Unit
    fun onError(e: Exception) = Unit
}
