package prac2.core

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import prac2.core.discount.DiscountPolicy
import prac2.core.discount.FixDiscountPolicy
import prac2.core.order.OrderService
import prac2.core.order.OrderServiceImpl
import prac2.core.repository.MemberRepo
import prac2.core.repository.MemoryMemberRepo
import prac2.core.service.MemberService
import prac2.core.service.MemberServiceImpl

@Configuration
class AppConfig  {

    @Bean
    fun memberRepo(): MemberRepo {
        println("Call Member Repo")
        return MemoryMemberRepo()
    }

    @Bean
    fun discountPolicy(): DiscountPolicy {
        return FixDiscountPolicy()
    }

    @Bean
    fun memberService(): MemberService {
        println("Call Member Service")
        return MemberServiceImpl( memberRepo() )
    }

    @Bean
    fun orderService(): OrderService {
        println("Call Order Service")
        return OrderServiceImpl( memberRepo() , discountPolicy())
    }
}
