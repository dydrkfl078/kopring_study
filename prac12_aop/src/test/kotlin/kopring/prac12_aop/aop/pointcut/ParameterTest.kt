package kopring.prac12_aop.aop.pointcut

import org.springframework.boot.test.context.SpringBootTest

import io.github.oshai.kotlinlogging.KotlinLogging
import kopring.prac12_aop.member.MemberService
import kopring.prac12_aop.member.MemberServiceImpl
import kopring.prac12_aop.member.annotation.ClassAop
import kopring.prac12_aop.member.annotation.MethodAop
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.junit.jupiter.api.Test
import org.springframework.aop.support.AopUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import

private val logger = KotlinLogging.logger {  }
@SpringBootTest
@Import(ParamAspect::class)
class ParameterTest {

    @Autowired
    private lateinit var memberService: MemberServiceImpl

    @Test
    fun success() {
        logger.info { " AopUtils.isAopProxy(memberService) = ${AopUtils.isAopProxy(memberService)}" }
        memberService.internal("파라미터 테스트 - String ")
    }
}

@Aspect
class ParamAspect() {

    @Pointcut("execution(* kopring.prac12_aop.member..*(..))")
    fun allMember(){}

    // 1. joinPoint 의 args 배열에 직접 접근하는 방법
    @Around("allMember()")
    fun logArgs1(joinPoint: ProceedingJoinPoint): Any {
        logger.info { " [joinPoint.args.first()]target = ${joinPoint.signature} args[0] = ${joinPoint.args[0] ?: null} " }
        return joinPoint.proceed() ?: Unit
    }

    // 2. pointcut 에서 args 속성을 사용하는 방법 --> 직접 배열에 접근하지 않는다.
    @Around("allMember() && args(arg1, ..)")
    fun logArgs2(joinPoint: ProceedingJoinPoint, arg1 : Any): Any {
        logger.info { " [around - args]target = ${joinPoint.signature} args[0] = ${joinPoint.args[0] ?: null} " }
        return joinPoint.proceed() ?: Unit
    }

    // 3. 파라미터를 사용하기 위한 축약버전
    @Before("allMember() && args(arg1, ..)")
    fun logArgs3(arg1 : String) {
        logger.info { " [before - args] arg = $arg1 " }
    }


    // 4. Proxy 객체를 전달 받는다.
    @Before("allMember() && this(obj)")
    fun logArgs4(obj : MemberService) {
        logger.info { " [this] obj = ${obj::class.simpleName} " }
    }

    // 5. Proxy 가 적용되는 실제 대상 객체를 전달 받는다.
    @Before("allMember() && target(obj)")
    fun logArgs5(obj : MemberService) {
        logger.info { " [target] obj = ${obj::class.simpleName} " }
    }

    // 6. 해당 타입의 Annotation 을 전달 받는다 ( target 으로 지정된 annotation 객체를 상속 받는 하위 클래스도 포인트 컷 범위에 해당한다. )
    @Before("allMember() && @target(obj)")
    fun logArgs6(obj : ClassAop) {
        logger.info { " [@target] obj = ${obj::class.simpleName} " }
    }

    // 7. 해당 타입의 Annotation 을 전달 받는다 ( within 으로 지정된 annotation 객체를 상속 받는 하위 클래스는 포인트 컷 범위에 해당하지 않는다. )
    @Before("allMember() && @within(obj)")
    fun logArgs7(obj : ClassAop) {
        logger.info { " [@within] obj = ${obj::class.simpleName} " }
    }

    // 8. 매서드 애노테이션 - @annotation
    @Before("allMember() && @annotation(methodAop)")
    fun logArgs8(methodAop : MethodAop) {
        logger.info { " [@annotation]method annotation value = ${methodAop.value} " }
    }
}