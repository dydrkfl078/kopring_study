package kopring.prac11_advanced.decorator.pure.code

import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {  }
class MessageDecorator(private val component: Component) : Component {
    override fun opertation(): String {

        val data = component.opertation()
        val decoResult = "*****${data}*****"

        logger.info { "Message decorator 적용 전 - $data, 적용 후 - $decoResult" }
        return decoResult
    }


}