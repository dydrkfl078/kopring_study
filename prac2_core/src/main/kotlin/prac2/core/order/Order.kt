package prac2.core.order

class Order(
    private val memberId: Long,
    private val itemName: String,
    private val itemPrice: Int,
    private val discountAmount: Int
) {

    fun getTotalPrice(): Int {
        return itemPrice - discountAmount
    }

    fun getMemberId(): Long {
        return memberId
    }

    fun getItemName(): String {
        return itemName
    }

    fun getItemPrice(): Int {
        return itemPrice
    }

    fun getDiscountAmount(): Int {
        return discountAmount
    }
}