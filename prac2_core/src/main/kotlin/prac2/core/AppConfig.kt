package prac2.core

import prac2.core.discount.FixDiscountPolicy
import prac2.core.order.OrderService
import prac2.core.order.OrderServiceImpl
import prac2.core.repository.MemoryMemberRepo
import prac2.core.service.MemberService
import prac2.core.service.MemberServiceImpl

object AppConfig  {
    fun memberService(): MemberService {
        return MemberServiceImpl( MemoryMemberRepo() )
    }

    fun orderService(): OrderService {
        return OrderServiceImpl( MemoryMemberRepo(),FixDiscountPolicy())
    }
}