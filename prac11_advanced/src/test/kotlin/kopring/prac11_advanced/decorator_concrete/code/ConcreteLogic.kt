package kopring.prac11_advanced.decorator_concrete.code

import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {  }
open class ConcreteLogic {
    open fun operation() {
        logger.info { "Concrete logic 1 실행" }
    }
}