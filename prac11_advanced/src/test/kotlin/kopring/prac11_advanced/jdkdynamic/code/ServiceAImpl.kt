package kopring.prac11_advanced.jdkdynamic.code

import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {  }
class ServiceAImpl : ServiceA{
    override fun call() {
        logger.info { "Service A 실행!" }
    }
}