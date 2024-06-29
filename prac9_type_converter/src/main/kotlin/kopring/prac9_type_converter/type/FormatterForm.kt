package kopring.prac9_type_converter.type

import org.springframework.format.annotation.DateTimeFormat
import org.springframework.format.annotation.NumberFormat
import java.time.LocalDateTime

data class FormatterForm (

    @field: NumberFormat(pattern = "#,###")
    val formNumber : Double,

    @field: DateTimeFormat(iso = DateTimeFormat.ISO.NONE, pattern = "yyyy,MM,dd HH:mm:ss:SSS")
    val date : LocalDateTime
)