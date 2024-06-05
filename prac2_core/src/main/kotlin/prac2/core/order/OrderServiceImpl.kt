package prac2.core.order

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import prac2.core.discount.DiscountPolicy
import prac2.core.discount.FixDiscountPolicy
import prac2.core.discount.MainDiscountPolicy
import prac2.core.discount.RateDiscountPolicy
import prac2.core.repository.MemberRepo
import prac2.core.repository.MemoryMemberRepo

@Component
class OrderServiceImpl(private val memberRepo: MemberRepo, @MainDiscountPolicy private val discountPolicy: DiscountPolicy): OrderService {

//    private val memberRepo : MemberRepo = MemoryMemberRepo()
//    private val discountPolicy : DiscountPolicy = FixDiscountPolicy()
//    private val discountPolicy : DiscountPolicy = RateDiscountPolicy()

    override fun createOrder(memberId: Long, itemName: String, itemPrice: Int): Order? {
        memberRepo.findById(memberId)?.let {
            val totalPrice = discountPolicy.discount(it, itemPrice)
            return Order(memberId, itemName, itemPrice, totalPrice)
        }?: return Order(memberId, itemName, itemPrice, 5000)
    }

    // todo 테스트용
    override fun getRepo():MemberRepo {
        return memberRepo
    }
}