package kopringprac.registration.domain.item

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.Range
import org.springframework.stereotype.Component

class Item(

    var id : Long? = null,
    @field:NotBlank
    var itemName : String = "",

    @field:NotNull
    @field:Range(min = 1000, max = 1000000)
    var price : Int?,

    @field:NotNull
    @field:Max(9999)
    var quantity : Int?,
    var open : Boolean = false,
    var regions : List<String> = listOf<String>(),
    var itemType : String = "",
    var deliveryCode : String = ""
)