package kopring.prac11_advanced.jdkdynamic.code

import io.github.oshai.kotlinlogging.KotlinLogging
private val logger = KotlinLogging.logger {  }

class ServiceBImpl : ServiceB{
    override fun call(str: String) {
        logger.info { "Service B 실행! str = $str" }
    }
}