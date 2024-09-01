package kopring.prac11_advanced.template.code

import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {  }
class SubClassLogic1 : AbstractTemplate() {

    override fun call (){
        logger.info { "비즈니스 로직 1 실행" }
    }
}