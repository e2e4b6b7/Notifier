package notifier.impl

import notifier.api.SiteParser
import org.jsoup.Jsoup
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DescribeNonEUDrivingLicenseParser : SiteParser<List<LocalDate>> {
    override val url: String
        get() = "https://termin.bremen.de/termine/suggest?mdt=707&select_cnc=1&cnc-8715=0&cnc-8716=0&cnc-8717=1&cnc-8718=0&cnc-8719=0&cnc-8720=0&cnc-8721=0&cnc-8722=0&cnc-8723=0&cnc-8724=0&cnc-8725=0&cnc-8726=0&cnc-8727=0&cnc-8728=0&cnc-8729=0&cnc-8730=0&cnc-8731=0"

    override fun parse(body: String): List<LocalDate> {
        return Jsoup
            .parseBodyFragment(body)
            .body()
            .getElementById("sugg_accordion")
            ?.children()
            .let { it ?: return emptyList() }
            .asSequence()
            .filter { it.nodeName() == "h3" }
            .map { it.text() }
            .map { date -> date.dropWhile { it != ' ' }.drop(1) }
            .map {
                val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
                LocalDate.parse(it, formatter)
            }
            .toList()
    }
}
