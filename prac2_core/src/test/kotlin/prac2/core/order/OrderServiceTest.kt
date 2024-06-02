package prac2.core.order

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import prac2.core.AppConfig
import prac2.core.discount.DiscountPolicy
import prac2.core.discount.FixDiscountPolicy
import prac2.core.member.Grade
import prac2.core.member.Member
import prac2.core.repository.MemberRepo
import prac2.core.repository.MemoryMemberRepo
import prac2.core.service.MemberServiceImpl

class OrderServiceTest {

    companion object {
        private val memberService = AppConfig.memberService()
        private val orderService = AppConfig.orderService()
    }

    @Test
    fun 할인정책이반영된주문을만든다 (){
        // given
        val member : Member = Member(1L, "Test Member", Grade.VIP)
        memberService.join(member)

        // when
        val order = orderService.createOrder(1L,"당근",10000)

        // then
        order?.getTotalPrice() shouldBe 9000
    }
}