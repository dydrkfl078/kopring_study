package kopring.prac9_type_converter.converter

import kopring.prac9_type_converter.type.IpPort
import org.springframework.core.convert.converter.Converter

class IpPortToStringConverter : Converter<IpPort, String> {

    override fun convert(source: IpPort): String? {
        return "${source.ip}:${source.port}"
    }
}