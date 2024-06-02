package prac2.core.discount

import prac2.core.member.Grade
import prac2.core.member.Member

class FixDiscountPolicy():DiscountPolicy {

    private val amount = 1000

    override fun discount(member: Member, price: Int ): Int {
        return if ( member.getGrade() == Grade.VIP ) { amount } else { 0 }
    }
}