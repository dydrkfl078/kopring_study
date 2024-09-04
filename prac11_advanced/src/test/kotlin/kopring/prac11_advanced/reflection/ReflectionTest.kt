package kopring.prac11_advanced.reflection

import io.github.oshai.kotlinlogging.KotlinLogging
import org.junit.jupiter.api.Test
import kotlin.reflect.KCallable
import kotlin.reflect.full.declaredMemberFunctions

private val logger = KotlinLogging.logger {  }
class ReflectionTest {

    @Test
    fun reflectionTest() {
        val callHelp = ReflectionLogic::class.declaredMemberFunctions

        val target = ReflectionLogic()
        dynamicCall(callHelp.find { it.name == ReflectionLogic.METHOD_HELP },target)
        dynamicCall(callHelp.find { it.name == ReflectionLogic.METHOD_DISPLAY },target)
    }

    private fun dynamicCall(method: KCallable<*>?, target: Any) {
        logger.info { "start" }
        method!!.call(target)
        logger.info { "end" }
    }
}

class ReflectionLogic() {

    companion object {
        const val METHOD_HELP = "help"
        const val METHOD_DISPLAY = "display"
    }
    fun help() {
        logger.info { "로직 - help 실행!" }
    }

    fun display() {
        logger.info { "로직 - display 실행!" }
    }
}