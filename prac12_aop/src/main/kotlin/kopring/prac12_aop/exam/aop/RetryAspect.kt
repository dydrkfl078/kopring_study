package kopring.prac12_aop.exam.aop

import kopring.prac12_aop.exam.annotation.Retry
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import io.github.oshai.kotlinlogging.KotlinLogging
private val logger = KotlinLogging.logger {  }

// 예외가 발생되어 원하는 결과를 얻지 못할 경우 n만큼 반복하는 로직.

@Aspect
class RetryAspect {

    @Around("@annotation(retry)")
    fun doRetry(joinPoint: ProceedingJoinPoint, retry: Retry) : Any {
        logger.info { "[retry] ${joinPoint.signature} / retry = $retry" }

        val maxRetry = retry.value
        var exceptionHolder : Exception? = null

        (1 .. maxRetry).forEach {
            try {
                logger.info { "[retry] try count = $it / $maxRetry" }
                return joinPoint.proceed()
            } catch (e: Exception) {
                exceptionHolder = e
            }
        }
        exceptionHolder?.let { throw it } ?: return Unit
    }
}