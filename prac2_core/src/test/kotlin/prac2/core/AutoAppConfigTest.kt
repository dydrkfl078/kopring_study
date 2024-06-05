package prac2.core

import io.kotest.core.annotation.AutoScan
import io.kotest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import prac2.core.order.OrderService
import prac2.core.service.MemberService

class AutoAppConfigTest(){

    @Test
    @DisplayName("ComponentScan 으로 스프링 컨테이너 생성, getBean 시 객체가 잘 꺼내와지는가")
    fun findBeanWithAutoAppConfig(){
        val ac : AnnotationConfigApplicationContext = AnnotationConfigApplicationContext(AutoAppConfig::class.java)

        ac.getBean(MemberService::class.java).shouldBeInstanceOf<MemberService>()
    }
}