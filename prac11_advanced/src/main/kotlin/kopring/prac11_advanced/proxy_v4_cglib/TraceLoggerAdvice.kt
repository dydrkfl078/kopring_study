package kopring.prac11_advanced.proxy_v4_cglib

import kopring.prac11_advanced.trace.TraceStatus
import kopring.prac11_advanced.trace.logger.TraceLogger
import org.aopalliance.intercept.MethodInvocation
import org.springframework.util.PatternMatchUtils

class TraceLoggerAdvice(
    private val trace: TraceLogger,
) : org.aopalliance.intercept.MethodInterceptor {

    override fun invoke(invocation: MethodInvocation): Any? {

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