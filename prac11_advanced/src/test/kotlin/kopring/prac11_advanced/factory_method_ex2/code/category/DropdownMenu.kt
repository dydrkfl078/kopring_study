package kopring.prac11_advanced.factory_method_ex2.code.category

import io.github.oshai.kotlinlogging.KotlinLogging
private val logger = KotlinLogging.logger {  }
class DropdownMenu : Category {

    override fun click() {
        logger.info { "드롭다운 메뉴 설정 로직 실행!" }
    }
}