package kopring.prac11_advanced.factory_method_ex2.code.category

import io.github.oshai.kotlinlogging.KotlinLogging
private val logger = KotlinLogging.logger {  }
class Title : Category {

    private var title = ""

    override fun click() {
        logger.info { "할 일 제목 클릭" }
    }

    fun changeTitle(newTitle : String) {
        title = newTitle
    }
}