package kopring.prac11_advanced.decorator.pure.code

import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {  }
class RealComponent : Component {
    override fun opertation(): String {
        logger.info { "Real component 실행" }

        return "data"
    }
}