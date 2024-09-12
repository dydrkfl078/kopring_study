package kopring.prac11_advanced.factory_method_ex2.code.category

import io.github.oshai.kotlinlogging.KotlinLogging
private val logger = KotlinLogging.logger {  }
class TextMemo : Category {

    override fun click() {
        logger.info { "텍스트 입력 로직 실행!" }
    }
}