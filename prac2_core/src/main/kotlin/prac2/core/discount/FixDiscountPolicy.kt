package prac2.core.discount

import org.springframework.stereotype.Component
import prac2.core.member.Grade
import prac2.core.member.Member

@Component
class FixDiscountPolicy():DiscountPolicy {

    private val amount = 1000

    override fun discount(member: Member, price: Int ): Int {
        return if ( member.getGrade() == Grade.VIP ) { amount } else { 0 }
    }
}