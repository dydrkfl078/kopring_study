package prac2.core

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.kotest.matchers.types.shouldBeSameInstanceAs
import org.apache.commons.logging.Log
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.beans.factory.NoUniqueBeanDefinitionException
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.beans.factory.getBean
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import prac2.core.repository.MemberRepo
import prac2.core.repository.MemoryMemberRepo
import prac2.core.service.MemberService
import prac2.core.service.MemberServiceImpl
import java.lang.reflect.Member

class SpringBeanTest {

    companion object {
        val ac = AnnotationConfigApplicationContext(AppConfig::class.java)
    }

    @Test
    @DisplayName("모든 스프링 빈 조회")
    fun findAllBean(){
        val beanDefinitionNames = ac.beanDefinitionNames
        beanDefinitionNames.forEach {
            val beanDefinition = ac.getBeanDefinition(it)

            if (beanDefinition.role == BeanDefinition.ROLE_APPLICATION) {
                println(" name = $it, object = ${ac.getBean(it)}")
            }
        }
    }

    @Test
    @DisplayName("빈 이름으로 조회")
    fun findBeanName(){
        val memberService = ac.getBean("memberService", MemberService::class.java)

        memberService.shouldBeInstanceOf<MemberServiceImpl>()
    }

    @Test
    @DisplayName("타입 타입으로 조회")
    fun findBeanType(){
        val memberService = ac.getBean(MemberService::class.java)

        memberService.shouldBeInstanceOf<MemberServiceImpl>()
    }

    @Test
    @DisplayName("빈 이름으로 조회 - 실패케이스")
    fun findBeanName_X(){
        shouldThrow<NoSuchBeanDefinitionException> {
            ac.getBean("xxxx", MemberService::class.java)
        }
    }

    @Test
    @DisplayName("타입으로 빈 조회 시 중복 타입이 존재 - 실패 케이스")
    fun findBeanByTypeDuplicate(){
        val ac = AnnotationConfigApplicationContext(TestConfig::class.java)
        shouldThrow<NoUniqueBeanDefinitionException>{
            ac.getBean( MemberServiceImpl::class.java)
        }
    }
}

@Configuration
class TestConfig() {

    @Bean
    fun memberRepo(): MemberRepo {
        return MemoryMemberRepo()
    }

    @Bean
    fun memberService1(): MemberService {
        return MemberServiceImpl( memberRepo() )
    }

    @Bean
    fun memberService2(): MemberService {
        return MemberServiceImpl( memberRepo() )
    }
}