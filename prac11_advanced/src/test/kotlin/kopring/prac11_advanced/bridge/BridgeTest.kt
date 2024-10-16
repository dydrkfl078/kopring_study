package kopring.prac11_advanced.bridge

import kopring.prac11_advanced.bridge.code.BasicChat
import kopring.prac11_advanced.bridge.code.GPT
import kopring.prac11_advanced.bridge.code.Gemini
import kopring.prac11_advanced.bridge.code.NonStorageChat
import org.junit.jupiter.api.Test

class BridgeTest {

    private val basicChat = BasicChat()
    private val basicChatGpt = GPT(basicChat)
    private val basicChatGemini = Gemini(basicChat)

    private val nonStorageChat = NonStorageChat()
    private val nonStorageChatGpt = GPT(nonStorageChat)
    private val nonStorageChatGemini = Gemini(nonStorageChat)


    @Test
    fun start() {
        basicChatGpt.apiRequest("")
        basicChatGemini.apiRequest("")

        nonStorageChatGpt.apiRequest("")
        nonStorageChatGemini.apiRequest("")

    }
}