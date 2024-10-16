package kopring.prac11_advanced.bridge.code

import io.github.oshai.kotlinlogging.KotlinLogging
private val logger = KotlinLogging.logger {  }
class NonStorageChat: AIMethod {
    override fun executeChat() {
        logger.info { "대화 내역이 저장되지 않는 채팅" }
    }
}