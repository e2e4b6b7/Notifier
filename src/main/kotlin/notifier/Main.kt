package notifier

import notifier.impl.AnmeldungRegistrationParser
import notifier.impl.CliLogger
import notifier.impl.NoFilter
import notifier.impl.SoundNotifier

fun main() {
    Executor(AnmeldungRegistrationParser(), NoFilter(), SoundNotifier(), 2000, CliLogger()).run()
}
