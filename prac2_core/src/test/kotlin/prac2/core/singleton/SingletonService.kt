package prac2.core.singleton

import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeSameInstanceAs
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import prac2.core.AppConfig
import prac2.core.order.OrderService
import prac2.core.service.MemberService

class SingletonService private constructor() {

    companion object {
        private val instance = SingletonService()
        fun getInstance(): SingletonService = instance


    }


}

class SingletonServiceTest(){

    val ac : ApplicationContext = AnnotationConfigApplicationContext(AppConfig::class.java)

    @Test
    @DisplayName("단일 객체로 구현한 서비스 객체가 싱글톤으로 생성된다.")
    fun singletonServiceIsSingleton(){
        val instance1 = SingletonService.getInstance()
        val instance2 = SingletonService.getInstance()

        instance1 shouldBeSameInstanceAs (instance2)
    }

    @Test
    @DisplayName("스프링 컨테이너의 빈으로 등록된 객체가 싱글톤으로 생성된다.")
    fun singletonSpringContainerInstance(){
        val instance1 = ac.getBean("memberService", MemberService::class.java)
        val instance2 = ac.getBean("memberService", MemberService::class.java)

        instance1.shouldBeSameInstanceAs(instance2)
    }

    @Test
    @DisplayName("스프링 컨테이너의 빈으로 등록된 객체 A가 매개변수로 주어지는 객체 B, C의 매개변수 A는 동일한 객체이다. ")
    fun singletonSpringBeanParam(){
        val instance1 = ac.getBean("memberService", MemberService::class.java)
        val instance2 = ac.getBean("orderService", OrderService::class.java)

        instance1.getRepo() shouldBeSameInstanceAs instance2.getRepo()
    }
}