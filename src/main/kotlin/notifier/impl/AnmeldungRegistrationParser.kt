package notifier.impl

import notifier.api.SiteParser
import org.jsoup.Jsoup

class AnmeldungRegistrationParser : SiteParser<String> {
    override val url: String
        get() = "https://www.service.bremen.de/dienstleistungen/wohnsitz-anmelden-zuzug-aus-dem-ausland-123039?reg=dienstleistung"

    override fun parse(body: String): String {
        val doc = Jsoup.parseBodyFragment(body).body()
        val el = doc.getElementsContainingText("Frühestmöglicher Termin in Bremen:")
        val timeNode = el.last()?.lastElementChild()
        return timeNode?.text() ?: "No appointments"
    }
}
