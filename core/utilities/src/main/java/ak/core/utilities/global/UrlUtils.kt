package ak.core.utilities.global

import java.net.MalformedURLException
import java.net.URL


object UrlUtils {

    private val url_Regex = Regex("(https?://)(www\\.)?[-a-zA-Z0-9@:%._\\+~#?&//=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%._\\+~#?&//=]*)")

    val findUrlLink: (String?) -> String? = {
        it?.split(' ')?.find { str -> str.matches("https?://.+".toRegex()) }
    }

    val codeUrl: (String?) -> String?
        get() = {
            it?.replace(
                '\u002f',// -> /
                '\u2204' // -> ∄
            )
                ?.replace(
                    '\u003f',// -> ?
                    '\u2203' // -> 	∃
                )
        }

    val decodeUrl: (String?) -> String?
        get() = {
            it?.replace(
                '\u2204', // -> ∄
                '\u002f' // -> /
            )
                ?.replace(
                    '\u2203', // -> 	∃
                    '\u003f' // -> ?
                )
        }



    fun isValidUrl(input: String): Boolean {
        if (input.isBlank()) return false
        return url_Regex.matches(input)
    }

    fun isValidUrl2(url: String): Boolean {
        return try {
            URL(url)
            true
        } catch (e: MalformedURLException) {
            false
        }
    }




    fun getDomainName(url: String): String? {
        return try {
            URL(url).host
        } catch (e: MalformedURLException) {
            null
        }
    }

    fun getScheme(url: String): String? {
        return try {
            URL(url).protocol
        } catch (e: MalformedURLException) {
            null
        }
    }

    fun getPath(url: String): String? {
        return try {
            URL(url).path
        } catch (e: MalformedURLException) {
            null
        }
    }

    fun getQueryParameters(url: String): Map<String, String>? {
        return try {
            val query = URL(url).query
            if (query != null) {
                query.split("&").associate {
                    val (key, value) = it.split("=")
                    key to value
                }
            } else {
                emptyMap()
            }
        } catch (e: MalformedURLException) {
            null
        }
    }

}