package kopring.prac12_aop.order.aop

import org.aspectj.lang.annotation.Aspect
import io.github.oshai.kotlinlogging.KotlinLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Pointcut

private val logger = KotlinLogging.logger {  }

@Aspect
class AspectV1 {

    // V1 - 포인트 컷을 분리하지 않는 Aspect
//    @Around("execution(* kopring.prac12_aop.order..*(..))")
//    fun doLog(joinPoint: ProceedingJoinPoint): Any {
//        logger.info { "[Log] ${joinPoint.signature}" }
//        return joinPoint.proceed() ?: Unit
//    }

    // V2 - 포인트 컷을 분리하는 Aspect
    // 다른 곳에서도 해당 포인트 컷을 재사용할 수 있다.
    @Pointcut("execution(* kopring.prac12_aop.order..*(..))")
    fun allOrder() {} // pointcut signature

    @Around("allOrder()")
    fun doLog(joinPoint: ProceedingJoinPoint): Any {
        logger.info { "[Log] ${joinPoint.signature}" }
        return joinPoint.proceed() ?: Unit
    }
}