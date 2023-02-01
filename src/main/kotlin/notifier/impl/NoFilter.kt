package notifier.impl

import notifier.api.NotificationFilter

class NoFilter<T> : NotificationFilter<T> {
    override fun shouldNotify(prev: T?, new: T) = true
}
