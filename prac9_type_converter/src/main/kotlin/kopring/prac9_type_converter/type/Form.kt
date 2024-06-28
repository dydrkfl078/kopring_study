package kopring.prac9_type_converter.type

import org.springframework.format.annotation.DateTimeFormat
import org.springframework.format.annotation.NumberFormat
import java.time.LocalDateTime

class FormatterForm (

    @NumberFormat(pattern = "###,###")
    val formNumber : Int,

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val date : LocalDateTime
) {
}