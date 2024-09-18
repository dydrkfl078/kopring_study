package kopring.prac12_aop.aop.pointcut

import kopring.prac12_aop.member.annotation.ClassAop
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Bean
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired

private val logger = KotlinLogging.logger {  }
@SpringBootTest
class AtTargetAtWithin {

    @org.springframework.boot.test.context.TestConfiguration
    class TestConfiguration {

        @Bean
        fun child() : Child {
            return Child()
        }

        @Bean
        fun parent() : Parent {
            return Parent()
        }

        @Bean
        fun atTargetAtWithinAspect() : AtTargetAtWithinAspect {
            return AtTargetAtWithinAspect()
        }
    }

    @Autowired
    private lateinit var parent: Parent

    @Autowired
    private lateinit var child : Child

    @Test
    fun targetAndWithinClassTest() {
        logger.info { "parent.parentMethod() 실행" }
        parent.parentMethod()

        logger.info { "child.parentMethod() 실행" }
        child.parentMethod()

        logger.info { "child.childMethod() 실행" }
        child.childMethod()
    }
}

@Aspect
class AtTargetAtWithinAspect() {

    // @target = 실행 객체가 설정된 타입의 Annotation 이 있는 경우에 조인 포인트로 설정된다.
    // --> target 으로 지정한 Annotation 이 붙은 객체를 상속하는 하위 클래스의 경우에도 포인트 컷 범위에 해당된다
//    @Around("execution(* kopring.prac12_aop.aop.pointcut..*(..)) && @target(kopring.prac12_aop.member.annotation.ClassAop)")
    fun atTarget(joinPoint : ProceedingJoinPoint): Any {
        logger.info { "[@target] = ${joinPoint.signature} " }
        return joinPoint ?: Unit
    }

    // @within = 주어진 Annotation 이 있는 타입 내부들만 조인포인트로 설정된다.
    // --> within 으로 지정한 Annotation 이 붙은 객체를 상속하는 하위 클래스의 경우에는 포인트 컷 범위에 해당되지 않는다.
    @Around("execution(* kopring.prac12_aop.aop.pointcut..*(..)) && @within(kopring.prac12_aop.member.annotation.ClassAop)")
    fun atWithin(joinPoint : ProceedingJoinPoint): Any {
        logger.info { "[@within] = ${joinPoint.signature} " }
        return joinPoint ?: Unit
    }
}

@ClassAop
open class Parent() {
    open fun parentMethod() {}
}


open class Child(): Parent() {

    override fun parentMethod() {

    }

    open fun childMethod() {}
}

