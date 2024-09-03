package kopring.prac11_advanced.decorator_concrete.code

import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {  }
class TimeProxy(private val concreteLogic: ConcreteLogic): ConcreteLogic() {

    override fun operation() {
        logger.info { "time proxy start" }
        val startTime = System.currentTimeMillis()
        concreteLogic.operation()

        val endTime = System.currentTimeMillis()
        logger.info { "result time = ${endTime - startTime}" }
    }
}