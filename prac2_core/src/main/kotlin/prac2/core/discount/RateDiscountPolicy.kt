package prac2.core.discount

import org.springframework.stereotype.Component
import prac2.core.member.Grade
import prac2.core.member.Member

@Component
@MainDiscountPolicy
class RateDiscountPolicy : DiscountPolicy{

    private val rate = 10

    override fun discount(member: Member, price: Int): Int {
        return if (member.getGrade() == Grade.VIP) { price * rate / 100 } else { price }
    }
}