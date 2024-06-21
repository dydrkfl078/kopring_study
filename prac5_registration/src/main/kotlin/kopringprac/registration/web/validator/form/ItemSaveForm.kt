package kopringprac.registration.web.validator.form

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import kopringprac.registration.web.validator.EditCheck
import kopringprac.registration.web.validator.SaveCheck
import org.hibernate.validator.constraints.Range

class ItemSaveForm (
    var id : Long? = null,
    @field:NotBlank
    var itemName : String = "",

    @field:NotNull
    @field:Range(min = 1000, max = 1000000, groups = [SaveCheck::class, EditCheck::class])
    var price : Int?,

    @field:NotNull
    @field:Max(9999)
    var quantity : Int?,
    var open : Boolean = false,
    var regions : List<String> = listOf<String>(),
    var itemType : String = "",
    var deliveryCode : String = ""
) {
}