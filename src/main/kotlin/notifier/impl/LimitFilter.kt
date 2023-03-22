package notifier.impl

import notifier.api.NotificationFilter
import java.time.Duration
import java.time.LocalDate

class LimitFilter(private val limit: Duration) : NotificationFilter<LocalDate> {
    override fun shouldNotify(prev: LocalDate?, new: LocalDate): Boolean {
        return Duration.between(LocalDate.now().atStartOfDay(), new.atStartOfDay()) < limit
    }
}
