package kopring.prac11_advanced.proxy_v6_aspect

import io.github.oshai.kotlinlogging.KotlinLogging
import kopring.prac11_advanced.trace.TraceStatus
import kopring.prac11_advanced.trace.logger.TraceLogger
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect

private val logger = KotlinLogging.logger {  }
@Aspect
class LogTraceAspect(private val trace : TraceLogger) {

    @Around("execution(* kopring.prac11_advanced.v0..*(..))")
    fun execute(joinPoint: ProceedingJoinPoint): Any {
        var status : TraceStatus? = null
        val message = joinPoint.signature.toShortString()

        try {
            status = trace.begin("$message AOP!")

            val result = joinPoint.proceed()

            trace.end(status)

            return result ?: Unit
        } catch (e: Exception) {
            trace.exception(status!!, e)
            throw e
        }
    }
}