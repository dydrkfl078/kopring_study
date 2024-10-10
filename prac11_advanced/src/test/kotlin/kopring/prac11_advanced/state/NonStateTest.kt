package kopring.prac11_advanced.state

import org.junit.jupiter.api.Test
import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {  }
class NonStateTest {

    private val category = Task()

    @Test
    fun nonStateTest(){

        category.write("넘0버1테2스3트4")
        category.setCategoryType(Task.CATEGORY_NUMBER)
        category.write("넘0버1테2스3트4")
    }
}

private class Task(private var categoryType: String = CATEGORY_TEXT) {

    companion object {
        const val CATEGORY_TEXT = "text"
        const val CATEGORY_DATE = "date"
        const val CATEGORY_NUMBER = "number"
    }

    fun setCategoryType(categoryType: String) {
        this.categoryType = categoryType
    }

    fun write(content: String) {

        when(categoryType) {
            CATEGORY_TEXT -> {
                logger.info { "TEXT = $content" }
            }
            CATEGORY_DATE -> {
                // 날짜 선택 로직
                logger.info { "DATE = 날짜 " }
            }
            CATEGORY_NUMBER -> {
                val result = content.replace(Regex("[^0-9]"), "")
                logger.info { "NUMBER = $result" }
            }
        }
    }
}