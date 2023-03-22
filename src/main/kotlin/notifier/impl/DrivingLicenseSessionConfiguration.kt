package notifier.impl

import notifier.api.SessionConfiguration

class DrivingLicenseSessionConfiguration : SessionConfiguration {
    override val cookieProviderUrl: String
        get() = "https://termin.bremen.de/termine/select2?md=2&lang=de_DE"
    override val cookieName: String
        get() = "TVWebSession"
}
