package prac2.core.order

import prac2.core.discount.DiscountPolicy
import prac2.core.discount.FixDiscountPolicy
import prac2.core.repository.MemberRepo
import prac2.core.repository.MemoryMemberRepo

class OrderServiceImpl: OrderService {

    private val memberRepo : MemberRepo = MemoryMemberRepo()
    private val discountPolicy : DiscountPolicy = FixDiscountPolicy()

    override fun createOrder(memberId: Long, itemName: String, itemPrice: Int): Order? {
        memberRepo.findById(memberId)?.let {
            val totalPrice = discountPolicy.discount(it, itemPrice)
            return Order(memberId, itemName, itemPrice, totalPrice)
        }?: return Order(memberId, itemName, itemPrice, 5000)
    }
}