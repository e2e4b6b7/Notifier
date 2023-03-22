package notifier

import notifier.impl.AnmeldungRegistrationParser
import notifier.impl.CliLogger
import notifier.impl.CompositeNewListFilter
import notifier.impl.DescribeNonEUDrivingLicenseParser
import notifier.impl.DrivingLicenseSessionConfiguration
import notifier.impl.LimitFilter
import notifier.impl.NoFilter
import notifier.impl.SoundNotifier
import notifier.impl.TomorrowFilter
import java.time.Duration

private val anmeldungExecutor get() = Executor(
    parser = AnmeldungRegistrationParser(),
    filter = NoFilter(),
    notifier = SoundNotifier(),
    cooldown = 2000, // 2 seconds
    logger = CliLogger(),
)

private val drivingLicenseExchangeExecutor get() = Executor(
    parser = DescribeNonEUDrivingLicenseParser(),
    filter = CompositeNewListFilter(TomorrowFilter(), LimitFilter(Duration.ofDays(90))),
    notifier = SoundNotifier(),
    cooldown = 5 * 60000, // 5 minutes
    logger = CliLogger(),
    sessionConfiguration = DrivingLicenseSessionConfiguration(),
)

private const val HELP = "Supported options are --anmeldung & --driving-license"

fun main(args: Array<String>) {
    when {
        args.isEmpty() -> anmeldungExecutor.run()
        args.size == 1 -> when (args.single()) {
            "--anmeldung" -> anmeldungExecutor.run()
            "--driving-license" -> drivingLicenseExchangeExecutor.run()
            "--help" -> println(HELP)
            else -> println("Unsupported command. $HELP")
        }
        else -> println("Too many arguments")
    }
}
