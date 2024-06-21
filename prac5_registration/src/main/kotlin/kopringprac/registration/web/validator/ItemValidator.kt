package kopringprac.registration.web.validator

import kopringprac.registration.domain.item.Item
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator

@Component
class ItemValidator : Validator{
    override fun supports(clazz: Class<*>): Boolean {
        return Item::class.java.isAssignableFrom(clazz)
    }

    override fun validate(target: Any, errors: Errors) {
        val item = target as Item

        if (item.itemName.isBlank()) {
            // 1. 오류가 발생한 필드 값을 지우는 방식
            // bindingResult.addError(FieldError("item","itemName","상품 이름을 입력해주세요."))


            // 2. 오류가 발생한 필드 값을 복원하는 방식
            // bindingResult.addError(FieldError("item","itemName", item.itemName, false, arrayOf("required_item.itemName"), null, "상품 이름을 입력해주세요."))

            errors.rejectValue("itemName", "required")
        }
        item.price?.let { price ->
            if (price < 1000 || price > 1000000 ){
                // 1. 오류가 발생한 필드 값을 지우는 방식
                // bindingResult.addError(FieldError("item","price","상품 가격은 1,000원 이상, 1,000,000 원 이하까지 허용 됩니다."))

                // 2. 오류가 발생한 필드 값을 복원하는 방식
                // bindingResult.addError(FieldError("item","price",item.price,false, arrayOf("range.item.price"), arrayOf(1000,1000000),"상품 가격은 1,000원 이상, 1,000,000 원 이하까지 허용 됩니다."))

                errors.rejectValue("price", "range", arrayOf(1000,1000000), null)
            }
        }

        item.quantity?.let { quantity ->
            if (quantity < 1 || quantity > 9999) {
                // 1. 오류가 발생한 필드 값을 지우는 방식
                // bindingResult.addError(FieldError("item","quantity","상품 수량은 1개 이상, 9999개 이하까지 허용 됩니다."))

                // 2. 오류가 발생한 필드 값을 복원하는 방식
                //bindingResult.addError(FieldError("item","quantity",item.quantity,false, arrayOf("max.item.quantity"),
                //    arrayOf(9999),"상품 수량은 1개 이상, 9999개 이하까지 허용 됩니다."))

                errors.rejectValue("quantity","max", arrayOf(9999),null)
            }
        }

        val price = item.price?:0
        val quantity = item.quantity?:0
        val totalPrice = price * quantity
        if (totalPrice < 10000) {
//            bindingResult.addError(ObjectError("item", arrayOf("totalPriceMin"), arrayOf(10000,totalPrice),"알 수 없는 오류가 발생했습니다. 관리자에게 문의해주세요"))

            errors.reject("totalPriceMin", arrayOf(1000,totalPrice),null)
        }
    }
}