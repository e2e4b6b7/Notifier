package notifier.impl

import notifier.api.NotificationFilter
import java.time.LocalDate

class TomorrowFilter : NotificationFilter<LocalDate> {
    override fun shouldNotify(prev: LocalDate?, new: LocalDate): Boolean {
        return new.atStartOfDay() > LocalDate.now().atStartOfDay()
    }
}
