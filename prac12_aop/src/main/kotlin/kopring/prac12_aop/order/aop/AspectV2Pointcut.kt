package kopring.prac12_aop.order.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import io.github.oshai.kotlinlogging.KotlinLogging
import org.aspectj.lang.annotation.Aspect

private val logger = KotlinLogging.logger {  }


// 외부의 Pointcut 을 참조하는 방법.
// @Aspect V3 로 인해서 주석처리.
class AspectV2Pointcut {

    @Around("kopring.prac12_aop.order.aop.Pointcuts.allOrder()")
    fun doTx(joinPoint: ProceedingJoinPoint): Any {
        try {
            logger.info { "[트랜잭션 시작] ${joinPoint.signature}" }

            val result = joinPoint.proceed()
            logger.info { "[트랜잭션 커밋] ${joinPoint.signature}" }

            return result ?: Unit
        }catch (e: Exception){
            logger.info { "[트랜잭션 롤백] ${joinPoint.signature}" }
            throw e
        }finally {
            logger.info { "[리로스 초기화] ${joinPoint.signature}" }
        }
    }

    @Around("kopring.prac12_aop.order.aop.Pointcuts.orderAndService()")
    fun doLog(joinPoint: ProceedingJoinPoint): Any {
        logger.info { "[Log] ${joinPoint.signature}" }
        return joinPoint.proceed() ?: Unit
    }
}