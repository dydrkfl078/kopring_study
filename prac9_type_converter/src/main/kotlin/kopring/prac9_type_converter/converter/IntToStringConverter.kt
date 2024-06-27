package kopring.prac9_type_converter.converter

import org.springframework.core.convert.converter.Converter

class IntToStringConverter : Converter<Int, String> {

    override fun convert(source: Int): String? {
        return source.toString()
    }
}