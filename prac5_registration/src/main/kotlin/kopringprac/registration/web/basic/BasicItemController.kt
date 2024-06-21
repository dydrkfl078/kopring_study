package kopringprac.registration.web.basic

import io.github.oshai.kotlinlogging.KotlinLogging
import kopringprac.registration.domain.item.DeliveryCodes
import kopringprac.registration.domain.item.Item
import kopringprac.registration.domain.item.ItemRepo
import kopringprac.registration.domain.item.ItemType
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import java.beans.PropertyEditorSupport
import java.lang.reflect.Field

private val logger = KotlinLogging.logger {  }

@Controller
@RequestMapping("/basic/items")
class BasicItemController(private val itemRepo: ItemRepo) {

    init {
        itemRepo.save(Item("testItemA", 50000, 30, false))
        itemRepo.save(Item("testItemB", 20000, 8, true))
    }

    @ModelAttribute("regions")
    fun regions(): LinkedHashMap<String, String> {
        val regions = LinkedHashMap<String, String>()
        regions["SEOUL"] = "서울"
        regions["ILSAN"] = "일산"
        regions["GURI"] = "구리"

        return regions
    }

    @ModelAttribute("itemTypes")
    fun itemTypes(): List<ItemType> {
        return ItemType.entries
    }

    @ModelAttribute("deliveryCodes")
    fun deliveryCodes(): List<DeliveryCodes> {
        val deliverCodeList = mutableListOf<DeliveryCodes>()
            .apply {
                add(DeliveryCodes("FAST","빠른 배송"))
                add(DeliveryCodes("NORMAL","일반 배송"))
                add(DeliveryCodes("SLOW","느린 배송"))
            }
        return deliverCodeList
    }

    @GetMapping
    fun items(model: Model): String {
        model.addAttribute("items", itemRepo.findAll())
        return "basic/items"
    }

    @GetMapping("/{itemId}")
    fun item(@PathVariable itemId: Long, model: Model): String {
        val findItem = itemRepo.findById(itemId)
        model.addAttribute("item", findItem)
        return "basic/item"
    }

    @GetMapping("add")
    fun addForm(model: Model): String {
        model.addAttribute("item",Item(price = null, quantity = null) )
        return "basic/addForm"
    }

    // @ModelAttribute 를 명시적으로 작성하여 자동으로 Item 객체를 만들어 준다.
    // @ModelAttribute("item") 으로 명시적으로 Model.setAttribute 해줄 수 있으나, 생략할 시 자동으로 클래스 이름으로 생성.
    // @ModelAttribute 자체도 생략이 가능하지만... 왠만하면 명시적으로 적어주는 것이 직관적이고 좋을 것 같다.
    @PostMapping("/add")
    fun saveV1(@ModelAttribute item : Item, bindingResult : BindingResult, rda : RedirectAttributes, model : Model): String{

        logger.info { "objectName = ${bindingResult.objectName}" }
        logger.info { "target = ${bindingResult.target}" }


        if (item.itemName.isBlank()) {
            // 1. 오류가 발생한 필드 값을 지우는 방식
            // bindingResult.addError(FieldError("item","itemName","상품 이름을 입력해주세요."))


            // 2. 오류가 발생한 필드 값을 복원하는 방식
            // bindingResult.addError(FieldError("item","itemName", item.itemName, false, arrayOf("required_item.itemName"), null, "상품 이름을 입력해주세요."))

            bindingResult.rejectValue("itemName", "required")
        }
        item.price?.let { price ->
            if (price < 1000 || price > 1000000 ){
                // 1. 오류가 발생한 필드 값을 지우는 방식
                // bindingResult.addError(FieldError("item","price","상품 가격은 1,000원 이상, 1,000,000 원 이하까지 허용 됩니다."))

                // 2. 오류가 발생한 필드 값을 복원하는 방식
                // bindingResult.addError(FieldError("item","price",item.price,false, arrayOf("range.item.price"), arrayOf(1000,1000000),"상품 가격은 1,000원 이상, 1,000,000 원 이하까지 허용 됩니다."))

                bindingResult.rejectValue("price", "range", arrayOf(1000,1000000), null)
            }
        }

        item.quantity?.let { quantity ->
            if (quantity < 1 || quantity > 9999) {
                // 1. 오류가 발생한 필드 값을 지우는 방식
                // bindingResult.addError(FieldError("item","quantity","상품 수량은 1개 이상, 9999개 이하까지 허용 됩니다."))

                // 2. 오류가 발생한 필드 값을 복원하는 방식
                //bindingResult.addError(FieldError("item","quantity",item.quantity,false, arrayOf("max.item.quantity"),
                //    arrayOf(9999),"상품 수량은 1개 이상, 9999개 이하까지 허용 됩니다."))

                bindingResult.rejectValue("quantity","max", arrayOf(9999),null)
            }
        }

        val price = item.price?:0
        val quantity = item.quantity?:0
        val totalPrice = price * quantity
        if (totalPrice < 10000) {
//            bindingResult.addError(ObjectError("item", arrayOf("totalPriceMin"), arrayOf(10000,totalPrice),"알 수 없는 오류가 발생했습니다. 관리자에게 문의해주세요"))

            bindingResult.reject("totalPriceMin", arrayOf(1000,totalPrice),null)
        }

        if (bindingResult.hasErrors()) {
            logger.info { "errors = ${bindingResult.allErrors}" }
            return "basic/addForm"
        }

        itemRepo.save(item)
        rda.addAttribute("saveItem",item.id)
        rda.addAttribute("state", true)

        return "redirect:/basic/items/{saveItem}"
    }

    @GetMapping("/{itemId}/edit")
    fun editForm(@PathVariable itemId : Long, model: Model): String {
        val item = itemRepo.findById(itemId)

        model.addAttribute("item", item)
        return "basic/editForm"
    }

    @PostMapping("/{itemId}/edit")
    fun edit(@PathVariable itemId: Long,@ModelAttribute item : Item): String {
        itemRepo.update(item.id!!,item)
        return "redirect:/basic/items/{itemId}"
    }
}
