package prac2.core.discount

import prac2.core.member.Member

interface DiscountPolicy {

    fun discount(member: Member, price : Int): Int
}