package kopring.prac11_advanced.proxy_v4_cglib

import kopring.prac11_advanced.trace.TraceStatus
import kopring.prac11_advanced.trace.logger.TraceLogger
import org.aopalliance.intercept.MethodInvocation
import org.springframework.cglib.proxy.MethodProxy
import java.lang.reflect.Method
import org.springframework.util.PatternMatchUtils

class TraceLoggerHandlerCglib(
    private val trace: TraceLogger,
    private val patterns: Array<String>
) : org.aopalliance.intercept.MethodInterceptor {

    override fun invoke(invocation: MethodInvocation): Any? {

        if (!PatternMatchUtils.simpleMatch(patterns,invocation.method.name)) {
            return invocation.proceed()
        }

        var status : TraceStatus? = null

        try {
            status = trace.begin("${invocation.javaClass}.${invocation.method.name} 실행!")

            val result = invocation.proceed()

            trace.end(status)

            return result ?: Unit

        } catch (e: Exception) {
            trace.exception(status!! ,e)
            throw e
        }
    }
}