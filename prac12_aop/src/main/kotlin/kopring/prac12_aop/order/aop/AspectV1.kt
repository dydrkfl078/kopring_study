package kopring.prac12_aop.order.aop

import org.aspectj.lang.annotation.Aspect
import io.github.oshai.kotlinlogging.KotlinLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around

private val logger = KotlinLogging.logger {  }

@Aspect
class AspectV1 {

    @Around("execution(* kopring.prac12_aop.order..*(..))")
    fun doLog(joinPoint: ProceedingJoinPoint): Any {
        logger.info { "[Log] ${joinPoint.signature}" }
        return joinPoint.proceed() ?: Unit
    }
}