package kopring.prac9_type_converter.formatter

import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.util.*

class MyNumberFormatterTest {

    private val myNumberFormatter = MyNumberFormatter()

    @Test
    fun print (){
        myNumberFormatter.print(1000, Locale.KOREAN) shouldBe "1,000"
    }

    @Test
    fun parse () {
        val result = myNumberFormatter.parse("1,000", Locale.KOREAN)
        result shouldBe 1000
    }
}