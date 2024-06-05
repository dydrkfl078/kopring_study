package prac2.core.autowired

import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.beans.factory.UnsatisfiedDependencyException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.lang.Nullable
import org.springframework.stereotype.Component
import java.lang.reflect.Member

class AutowiredOptionTest {

    @Test
    fun autowiredOption(){
        val ac = AnnotationConfigApplicationContext(AutowiredOptionTestConfig::class.java)

        shouldThrow<NoSuchBeanDefinitionException> {
            ac.getBean(Member::class.java)
        }
    }
}

@Component
class AutowiredOptionTestConfig {

    @Autowired(required = false)
    fun setNullBean1(member: Member){
        println("setNullBean1 = $member")
    }

    @Autowired
    fun setNullBean2(@Nullable member: Member?){
        println("setNullBean2 = $member")
    }
}