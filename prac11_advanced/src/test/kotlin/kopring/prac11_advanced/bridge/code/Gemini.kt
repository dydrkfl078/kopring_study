package kopring.prac11_advanced.bridge.code

import io.github.oshai.kotlinlogging.KotlinLogging
private val logger = KotlinLogging.logger {  }

class Gemini(aiMethod: AIMethod): AIModel(aiMethod) {
    override fun apiRequest(query: String) {

        logger.info { "====== 제미나이 채팅 시작 ======" }
        aiMethod.executeChat()
    }
}