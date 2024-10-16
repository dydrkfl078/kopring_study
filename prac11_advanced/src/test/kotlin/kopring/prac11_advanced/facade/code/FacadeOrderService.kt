package kopring.prac11_advanced.facade.code

class FacadeOrderService (
    private val order: FacadeOrder,
    private val purchase: FacadePurchase,
    private val orderMessage: FacadeOrderMessage
) {
    fun startOrderProcess() {
        order.startOrder()
        purchase.startPurchase()
        orderMessage.sendNewOrderMessage()
    }
}