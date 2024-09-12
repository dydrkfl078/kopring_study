package kopring.prac11_advanced.factory_method_ex2.code.category

import io.github.oshai.kotlinlogging.KotlinLogging
private val logger = KotlinLogging.logger {  }
class Calendar(firstDayOfWeekIsMon: Boolean) : Category {

    val firstDayOfWeek = firstDayOfWeekIsMon

    override fun click() {
        logger.info { "캘린더 날짜 지정 로직 실행!" }
    }
}