package kopring.prac11_advanced.jdkdynamic.code

import io.github.oshai.kotlinlogging.KotlinLogging
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

private val logger = KotlinLogging.logger {  }
class TimeInvocationHandler(
    private val target : Any
) : InvocationHandler {

    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any? {
        val startTime = System.currentTimeMillis()
        logger.info { "Time proxy 실행!" }

        val result = method?.invoke(target, *(args ?: emptyArray()))

        logger.info { "Time proxy 종료! result time = ${System.currentTimeMillis() - startTime}" }
        return result
    }
}