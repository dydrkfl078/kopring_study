package kopring.prac11_advanced.bridge.code

import io.github.oshai.kotlinlogging.KotlinLogging
private val logger = KotlinLogging.logger {  }
class BasicChat : AIMethod {
    override fun executeChat() {
        logger.info { "대화 내역이 저장되는 채팅" }
    }
}