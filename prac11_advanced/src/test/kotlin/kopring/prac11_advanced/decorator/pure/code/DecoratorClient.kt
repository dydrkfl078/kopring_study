package kopring.prac11_advanced.decorator.pure.code

import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {  }
class DecoratorClient(private val component: Component) {

    fun execute() {
        val result = component.opertation()
        logger.info { "result = $result" }
    }
}