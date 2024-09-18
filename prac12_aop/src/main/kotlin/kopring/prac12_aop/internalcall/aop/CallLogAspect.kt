package kopring.prac12_aop.internalcall.aop

import org.aspectj.lang.annotation.Aspect

import io.github.oshai.kotlinlogging.KotlinLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around

private val logger = KotlinLogging.logger {  }
@Aspect
class CallLogAspect {

    @Around("execution(* kopring.prac12_aop.internalcall.*.*(..))")
    fun callLog(joinPoint: ProceedingJoinPoint): Any {
        logger.info { "obj = ${joinPoint.signature}" }
        return joinPoint.proceed() ?: Unit
    }
}