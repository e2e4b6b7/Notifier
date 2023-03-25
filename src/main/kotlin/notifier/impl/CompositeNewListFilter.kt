package notifier.impl

import notifier.api.NotificationFilter

class CompositeNewListFilter<T>(
    private vararg val filters: NotificationFilter<T>,
) : NotificationFilter<List<T>> {
    override fun shouldNotify(prev: List<T>?, new: List<T>): Boolean {
        return new.any { newElem ->
            filters.all {
                it.shouldNotify(null, newElem)
            }
        }
    }
}
