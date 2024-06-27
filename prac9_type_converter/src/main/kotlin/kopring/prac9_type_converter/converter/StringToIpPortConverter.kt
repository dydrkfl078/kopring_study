package kopring.prac9_type_converter.converter

import io.github.oshai.kotlinlogging.KotlinLogging
import kopring.prac9_type_converter.type.IpPort
import org.springframework.core.convert.converter.Converter

private val logger = KotlinLogging.logger {  }

class StringToIpPortConverter : Converter<String, IpPort> {

    override fun convert(source: String): IpPort {
        source.split(":").run {
            logger.info { "StringToIpPortConverter" }
            return IpPort(this[0], this[1].toInt())
        }
    }
}