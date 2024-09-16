package kopring.prac12_aop.order.aop

import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import io.github.oshai.kotlinlogging.KotlinLogging
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.AfterThrowing

private val logger = KotlinLogging.logger {  }

// Pointcut 으로 지정된 타겟 메서드 호출 전, 호출된 후, 예외 발생, finally 각각의 상황에 advice 제공.
// @Around 와는 다르게, 타겟 메서드 실행에 아무런 영향을 줄 수 없다.
// @Around 외에 Advice 는 JoinPoint 를 첫번째 파라미터를 사용하며, 생략할 수도 있다.

@Aspect
class AspectV4SeparateAdvice {

    @Before("kopring.prac12_aop.order.aop.Pointcuts.allService()")
    fun txBegin(joinPoint: JoinPoint){
        logger.info { "[트랜잭션 시작] ${joinPoint.signature}" }
    }

    // AfterRetuning 은 Around 와 다르게, 프로세스에서 실제로 반환되는 값을 변경할 수는 없다.
    // 프로세스의 결과값인 retuning 객체를 얻어올 순 있다. 단순히 값을 가져오는 용도.
    // ☆ 속성에 사용된 이름과 동일한 파라미터 이름을 사용해야 한다.
    @AfterReturning(value = "kopring.prac12_aop.order.aop.Pointcuts.allService()", returning = "returning")
    fun txReturning(joinPoint: JoinPoint, returning: Any ) {
        logger.info { "[트랜잭션 커밋] ${joinPoint.signature} return : $returning" }
    }

    @AfterThrowing(value = "kopring.prac12_aop.order.aop.Pointcuts.allService()", throwing = "ex")
    fun txThrowing(joinPoint: JoinPoint, ex: Exception) {
        logger.info { "[트랜잭션 롤백] ${joinPoint.signature} ex = $ex" }
    }

    @After("kopring.prac12_aop.order.aop.Pointcuts.allService()")
    fun txFinally(joinPoint: JoinPoint) {
        logger.info { "[트랜잭션 시작] ${joinPoint.signature}" }
    }
}