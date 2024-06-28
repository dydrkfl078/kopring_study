package kopring.prac9_type_converter.formatter

import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.matchers.equality.shouldBeEqualUsingFields
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
import kopring.prac9_type_converter.converter.IpPortToStringConverter
import kopring.prac9_type_converter.converter.StringToIpPortConverter
import kopring.prac9_type_converter.type.IpPort
import org.junit.jupiter.api.Test
import org.springframework.core.convert.support.DefaultConversionService
import org.springframework.format.support.DefaultFormattingConversionService

class FormatterConversionServiceTest {

    @Test
    fun formattingConversionService(){
        val conversionService = DefaultFormattingConversionService()

        // Converter
        conversionService.addConverter(StringToIpPortConverter())
        conversionService.addConverter(IpPortToStringConverter())

        // Formatter
        conversionService.addFormatter(MyNumberFormatter())

        // 컨버터 사용
        conversionService.convert("127.0.0.1:8080",IpPort::class.java)?.shouldBeEqualToComparingFields((IpPort("127.0.0.1",8080)))

        conversionService.convert(IpPort("127.0.0.1",8080),String::class.java)?.shouldBeEqual ("127.0.0.1:8080")

        // 포매터 사용
        conversionService.convert("1,000",Number::class.java) shouldBe 1000
        conversionService.convert(1000,String::class.java) shouldBe "1,000"
    }
}