package kopring.prac9_type_converter.formatter

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.format.Formatter
import java.text.NumberFormat
import java.util.*
import javax.swing.text.NumberFormatter

private val logger = KotlinLogging.logger {  }

class MyNumberFormatter : Formatter<Number> {
    override fun print(`object`: Number, locale: Locale): String {
        logger.info { "MyNumberFormatter object = [$`object`]" }
        return NumberFormat.getInstance(locale).format(`object`)
    }

    override fun parse(text: String, locale: Locale): Number {
        logger.info { "MyNumberFormatter text = [$text] locale = [$locale]" }
        return NumberFormat.getInstance(locale).parse(text)
    }
}