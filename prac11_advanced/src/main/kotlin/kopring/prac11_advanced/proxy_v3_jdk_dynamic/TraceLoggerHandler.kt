package kopring.prac11_advanced.proxy_v3_jdk_dynamic

import jdk.dynalink.NoSuchDynamicMethodException
import kopring.prac11_advanced.trace.TraceStatus
import kopring.prac11_advanced.trace.logger.TraceLogger
import org.springframework.util.PatternMatchUtils
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

class TraceLoggerHandler (
    private val target: Any,
    private val trace : TraceLogger,
    private val patterns : Array<String>
): InvocationHandler {

    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any? {

        // 메서드 이름이 특정 패턴에 해당 하지 않으면, decoration 적용 X
        if (!PatternMatchUtils.simpleMatch(patterns, method?.name)) {
            method?.invoke(target, *(args ?: emptyArray()))
        }

        var status : TraceStatus? = null

        try {
            status = trace.begin(method?.let { "${it.declaringClass.name}.${it.name} 실행!" }?: throw NoSuchDynamicMethodException("no such dynamic method exception"))

            val result = method.invoke(target, *(args ?: emptyArray()))

            trace.end(status)

            return result
        }catch (e: Exception) {
            trace.exception(status!!, e)
            throw e
        }
    }
}