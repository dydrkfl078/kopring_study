package kopring.prac12_aop.aop.pointcut

import org.junit.jupiter.api.Test
import io.github.oshai.kotlinlogging.KotlinLogging
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import kopring.prac12_aop.member.MemberServiceImpl
import org.springframework.aop.aspectj.AspectJExpressionPointcut
import kotlin.reflect.full.functions
import kotlin.reflect.jvm.javaMethod

private val logger = KotlinLogging.logger {  }
class ExecutionTest {

    private val pointcut = AspectJExpressionPointcut()
    private val myMethod = MemberServiceImpl::class.functions.find { it.name == "call" || it.returnType == String::class }

    @Test
    fun printMethod() {
        // fun kopring.prac12_aop.member.MemberServiceImpl.call(kotlin.String): kotlin.String
        logger.info { "$myMethod" }
    }

    // 가장 자세하게 지정한 포인트컷
    @Test
    fun exactMatch(){
        pointcut.expression = "execution(public String kopring.prac12_aop.member.MemberServiceImpl.call(String))"
        pointcut.matches(myMethod?.javaMethod!!, MemberServiceImpl::class.java).shouldBeTrue()
    }

    // 가장 많이 생략한 포인트 컷
    @Test
    fun allMatch(){
        pointcut.expression = "execution(* *(..))"
        pointcut.matches(myMethod?.javaMethod!!, MemberServiceImpl::class.java ).shouldBeTrue()
    }

    // 메서드 이름을 통해 찾기 -> 패키지 생략 가능.
    @Test
    fun nameMatch1(){
        pointcut.expression = "execution(* call(..))"
        pointcut.matches(myMethod?.javaMethod!!, MemberServiceImpl::class.java ).shouldBeTrue()
    }

    @Test
    fun nameMatch2(){
        pointcut.expression = "execution(* cal*(..))"
        pointcut.matches(myMethod?.javaMethod!!, MemberServiceImpl::class.java ).shouldBeTrue()
    }

    @Test
    fun packageMatch1(){
        pointcut.expression = "execution(* kopring.prac12_aop.member.*.*(..))"
        pointcut.matches(myMethod?.javaMethod!!, MemberServiceImpl::class.java ).shouldBeTrue()
    }

    // 하위 패키지가 어떤 것이든 가능함을 명시하기 위해서는 . 한개 더 추가해주어야 한다.
    @Test
    fun packageMatch2(){
        pointcut.expression = "execution(* kopring.prac12_aop..*.*(..))"
        pointcut.matches(myMethod?.javaMethod!!, MemberServiceImpl::class.java ).shouldBeTrue()
    }

    @Test
    fun packageMatch3(){
        pointcut.expression = "execution(* kopr*..*.*(..))"
        pointcut.matches(myMethod?.javaMethod!!, MemberServiceImpl::class.java ).shouldBeTrue()
    }

    // 상위 클래스를 오버라이드(상속 or 구현) 한 메서드는 상위 클래스를 지정해도 pointcut 이 적용된다.
    @Test
    fun typeMatchSuperType(){
        pointcut.expression = "execution(* *..MemberService.*(..))"
        pointcut.matches(myMethod?.javaMethod!!, MemberServiceImpl::class.java).shouldBeTrue()
    }

    // 하위 모듈 메서드는 상위 모듈 타입에 적용 스코프에 들지 않기 때문에 적용되지 않음.
    @Test
    fun typeMatchInternal(){
        pointcut.expression = "execution(* *..MemberService.*(..))"
        val internalMethod = MemberServiceImpl::class.functions.find { it.name == "internal" }
        pointcut.matches(internalMethod?.javaMethod!!, MemberServiceImpl::class.java).shouldBeFalse()
    }

    // 메서드 파라미터 매치 (Any)
    @Test
    fun argsMatch(){
        pointcut.expression = "execution(* *(String))"
        pointcut.matches(myMethod?.javaMethod!!, MemberServiceImpl::class.java).shouldBeTrue()
    }

    // 메서드 파라미터 매치 - 파라미터가 없는 경우만 적용 ( 빈 괄호 )
    @Test
    fun noArgsMatch(){
        pointcut.expression = "execution(* *())"
        pointcut.matches(myMethod?.javaMethod!!, MemberServiceImpl::class.java).shouldBeFalse()
    }

    // 메서드 파라미터 매치 - 파라미터 갯수 제한
    @Test
    fun argsMatchOnlyOneArg(){
        pointcut.expression = "execution(* *(*))"
        pointcut.matches(myMethod?.javaMethod!!, MemberServiceImpl::class.java).shouldBeTrue()
    }

    // 메서드 파라미터 매치 - 파라미터 타입, 갯수 제한
    @Test
    fun argsMatchOnlyOneArgType(){
        pointcut.expression = "execution(* *(String))"
        pointcut.matches(myMethod?.javaMethod!!, MemberServiceImpl::class.java).shouldBeTrue()
    }

    // 메서드 파라미터 매치 - 첫번째 파라미터 타입 제한
    @Test
    fun argsMatchFirstArgType(){
        pointcut.expression = "execution(* *(String, ..))"
        pointcut.matches(myMethod?.javaMethod!!, MemberServiceImpl::class.java).shouldBeTrue()
    }

    // 메서드 파라미터 매치 - 메서드 파라미터 갯수 제한 , 로 갯수 지정
    @Test
    fun argsMatchArgCount2(){
        pointcut.expression = "execution(* *(String, *, *, *))"
        pointcut.matches(myMethod?.javaMethod!!, MemberServiceImpl::class.java).shouldBeFalse()
    }

    // 메서드 파라미터 매치 - 파라미터 갯수, 타입 제한 X
    @Test
    fun argsMatchAny(){
        pointcut.expression = "execution(* *(..))"
        pointcut.matches(myMethod?.javaMethod!!, MemberServiceImpl::class.java).shouldBeTrue()
    }

}