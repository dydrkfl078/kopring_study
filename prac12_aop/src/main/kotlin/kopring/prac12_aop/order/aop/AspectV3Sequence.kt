package kopring.prac12_aop.order.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.core.annotation.Order
import io.github.oshai.kotlinlogging.KotlinLogging
private val logger = KotlinLogging.logger {  }


// V4 Advice 분리하기 때문에 주석처리
@Aspect
@Order(2)
class LogAspect() {
    @Around("kopring.prac12_aop.order.aop.Pointcuts.orderAndService()")
    fun doLog(joinPoint: ProceedingJoinPoint): Any {
        logger.info { "[Log] ${joinPoint.signature}" }
        return joinPoint.proceed() ?: Unit
    }
}

// V4 Advice 분리하기 때문에 주석처리
@Aspect
@Order(1)
class TxAspect() {
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
}