package kopring.prac12_aop.aop.pointcut

import io.github.oshai.kotlinlogging.KotlinLogging
import kopring.prac12_aop.member.MemberService
import kopring.prac12_aop.member.MemberServiceImpl
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import

private val logger = KotlinLogging.logger {  }

@SpringBootTest
class AtAnnotationTest {

    @Autowired
    private lateinit var memberService : MemberServiceImpl

    @TestConfiguration
    class TestConfig() {

        @Bean
        fun atAspectMethodAop() : AspectMethodAop {
            return AspectMethodAop()
        }
    }

    @Test
    fun atAnnotationTest() {
        memberService.internal("테스트1")
    }
}

@Aspect
class AspectMethodAop() {

    @Around("@annotation(kopring.prac12_aop.member.annotation.MethodAop)")
    fun aspectMethodAop(joinPoint : ProceedingJoinPoint): Any {
        logger.info { "[annotation] = ${joinPoint.signature}" }
        return joinPoint.proceed() ?: Unit
    }
}