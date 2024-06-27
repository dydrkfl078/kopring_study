package kopring.prac9_type_converter.converter

import org.springframework.core.convert.converter.Converter

class StringToIntConverter : Converter<String, Int>{

    override fun convert(source: String): Int? {
        return source.toIntOrNull()
    }
}