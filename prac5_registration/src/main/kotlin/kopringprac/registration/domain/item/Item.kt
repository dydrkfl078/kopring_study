package kopringprac.registration.domain.item

class Item(
    var itemName : String,
    var price : Int?,
    var quantity : Int?
) {
    var id : Long? = null
}