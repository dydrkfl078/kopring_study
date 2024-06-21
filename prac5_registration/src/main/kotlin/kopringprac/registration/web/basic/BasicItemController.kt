package kopringprac.registration.web.basic

import io.github.oshai.kotlinlogging.KotlinLogging
import kopringprac.registration.domain.item.DeliveryCodes
import kopringprac.registration.domain.item.Item
import kopringprac.registration.domain.item.ItemRepo
import kopringprac.registration.domain.item.ItemType
import kopringprac.registration.web.validator.ItemValidator
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import java.beans.PropertyEditorSupport
import java.lang.reflect.Field
import kotlin.math.log

private val logger = KotlinLogging.logger {  }

@Controller
@RequestMapping("/basic/items")
class BasicItemController(private val itemRepo: ItemRepo) {

    init {
        itemRepo.save(Item(itemName = "testItemA", price = 50000, quantity =  30, open = false))
        itemRepo.save(Item(itemName = "testItemB", price = 20000, quantity = 8, open = true))
    }

    @InitBinder
    fun integerBinder(binder: WebDataBinder) {
        binder.registerCustomEditor(Int::class.java, IntEditor())
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
    fun saveV1(@Validated @ModelAttribute item : Item, bindingResult : BindingResult, rda : RedirectAttributes, model : Model): String{

        logger.info { "target = ${bindingResult.target}" }

        val price = item.price?:0
        val quantity = item.quantity?:0
        val totalPrice = price * quantity
        if (totalPrice < 10000) {
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
    fun edit(@PathVariable itemId: Long, @Validated @ModelAttribute item : Item, bindingResult: BindingResult, rda: RedirectAttributes): String {

        logger.info { "target = ${bindingResult.target}" }

        val price = item.price?:0
        val quantity = item.quantity?:0
        val totalPrice = price * quantity
        if (totalPrice < 10000) {
            bindingResult.reject("totalPriceMin", arrayOf(1000,totalPrice),null)
        }

        if (bindingResult.hasErrors()) {
            logger.info { "errors = ${bindingResult.allErrors}" }
            logger.info { "item.id = ${item.id}" }
            logger.info { "item.id = ${item.itemName}" }
            logger.info { "item.itemType = ${item.itemType}" }

            return "basic/editForm"
        }

        itemRepo.update(item.id!!,item)
        return "redirect:/basic/items/{itemId}"
    }
}

class IntEditor : PropertyEditorSupport() {
    override fun setAsText(text: String?) {
        value = text?.toIntOrNull()
    }
}