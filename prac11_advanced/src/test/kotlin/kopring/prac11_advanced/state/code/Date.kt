package kopring.prac11_advanced.state.code

import io.github.oshai.kotlinlogging.KotlinLogging
private val logger = KotlinLogging.logger {  }
class Date : Category {

    override fun write(content: String) {
        // 날짜 선택 로직
        logger.info { "DATE = 날짜 " }
    }
}