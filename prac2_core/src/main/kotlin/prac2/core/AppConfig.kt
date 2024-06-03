package prac2.core

import prac2.core.discount.DiscountPolicy
import prac2.core.discount.FixDiscountPolicy
import prac2.core.order.OrderService
import prac2.core.order.OrderServiceImpl
import prac2.core.repository.MemberRepo
import prac2.core.repository.MemoryMemberRepo
import prac2.core.service.MemberService
import prac2.core.service.MemberServiceImpl

object AppConfig  {

    private fun memberRepo(): MemberRepo {
        return MemoryMemberRepo()
    }

    private fun discountPolicy(): DiscountPolicy {
        return FixDiscountPolicy()
    }

    fun memberService(): MemberService {
        return MemberServiceImpl( memberRepo() )
    }

    fun orderService(): OrderService {
        return OrderServiceImpl( memberRepo() , discountPolicy())
    }
}