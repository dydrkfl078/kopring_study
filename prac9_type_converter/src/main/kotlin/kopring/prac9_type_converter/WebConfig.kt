package kopring.prac9_type_converter

import kopring.prac9_type_converter.converter.IntToStringConverter
import kopring.prac9_type_converter.converter.IpPortToStringConverter
import kopring.prac9_type_converter.converter.StringToIntConverter
import kopring.prac9_type_converter.converter.StringToIpPortConverter
import kopring.prac9_type_converter.formatter.MyNumberFormatter
import org.springframework.context.annotation.Configuration
import org.springframework.format.FormatterRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {

    override fun addFormatters(registry: FormatterRegistry) {
//        registry.addConverter(IntToStringConverter())
//        registry.addConverter(StringToIntConverter())
//        registry.addConverter(IpPortToStringConverter())
//        registry.addConverter(StringToIpPortConverter())

        registry.addFormatter(MyNumberFormatter())
    }
}