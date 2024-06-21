package kopringprac.registration.domain.item

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import kopringprac.registration.web.validator.EditCheck
import kopringprac.registration.web.validator.SaveCheck
import org.hibernate.validator.constraints.Range
import org.springframework.stereotype.Component

class Item(

    var id : Long? = null,
    @field:NotBlank(groups = [SaveCheck::class, EditCheck::class])
    var itemName : String = "",

    @field:NotNull (groups = [SaveCheck::class, EditCheck::class])
    @field:Range(min = 1000, max = 1000000, groups = [SaveCheck::class, EditCheck::class])
    var price : Int?,

    @field:NotNull( groups = [SaveCheck::class, EditCheck::class] )
    @field:Max(9999, groups = [EditCheck::class])
    var quantity : Int?,
    var open : Boolean = false,
    var regions : List<String> = listOf<String>(),
    var itemType : String = "",
    var deliveryCode : String = ""
)