package prac2.core.autowired

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import prac2.core.AutoAppConfig
import prac2.core.discount.DiscountPolicy
import prac2.core.member.Grade
import prac2.core.member.Member

class AllBeanTest {

    @Test
    fun findAllBean(){
        val ac : ApplicationContext = AnnotationConfigApplicationContext(AutoAppConfig::class.java,TestDiscountService::class.java)
        val memberA = Member(1L, "memberA", Grade.VIP)
        val dcService = ac.getBean(TestDiscountService::class.java)
        fun discountPriceA(): Int {
            return dcService.discount(memberA, 10000, "fixDiscountPolicy")
        }

        fun discountPriceB(): Int {
            return dcService.discount(memberA, 20000, "rateDiscountPolicy")
        }

        discountPriceA() shouldBe 1000
        discountPriceB() shouldBe 2000
    }
}

class TestDiscountService(private val policyMap: Map<String, DiscountPolicy>, private val policies : List<DiscountPolicy>) {

    init {
        println(policies)
        println(policyMap)
    }

    fun discount (member: Member, price: Int,discountCode: String): Int {
        val discountPolicy : DiscountPolicy = policyMap[discountCode]!!
        return discountPolicy.discount(member, price)
    }
}