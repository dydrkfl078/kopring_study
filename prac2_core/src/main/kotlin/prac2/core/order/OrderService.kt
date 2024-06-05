package prac2.core.order

import prac2.core.repository.MemberRepo

interface OrderService {
    fun createOrder(memberId: Long, itemName: String, itemPrice: Int) : Order?

    // todo 테스트용
    fun getRepo(): MemberRepo
}