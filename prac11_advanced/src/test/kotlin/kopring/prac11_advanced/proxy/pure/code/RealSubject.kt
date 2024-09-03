package kopring.prac11_advanced.proxy.pure.code

import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {  }
class RealSubject(): Subject {
    override fun operation(): String {
        logger.info { "실제 객체 호출" }
        Thread.sleep(1000L)

        return "data"
    }
}