package kopringprac.registration.domain.item

class Item(
    var itemName : String = "",
    var price : Int = 0,
    var quantity : Int = 0,
    var open : Boolean = false
) {
    var id : Long? = null
    var regions = listOf<String>()
    var itemType : String = ""
    var deliveryCode : String = ""
}