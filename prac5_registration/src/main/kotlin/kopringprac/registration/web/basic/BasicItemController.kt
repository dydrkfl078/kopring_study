package kopringprac.registration.web.basic

import io.github.oshai.kotlinlogging.KotlinLogging
import kopringprac.registration.domain.item.DeliveryCodes
import kopringprac.registration.domain.item.Item
import kopringprac.registration.domain.item.ItemRepo
import kopringprac.registration.domain.item.ItemType
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes

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
        model.addAttribute("item", Item())
        return "basic/addForm"
    }

//    @PostMapping("add")
//    fun save(
//        @ModelAttribute
//        model: Model
//    ): String {
//        val item = Item(itemName,price,quantity)
//        itemRepo.save(item)
//
//        model.addAttribute("item", item)
//        return "basic/item"
//    }

    // @ModelAttribute 를 명시적으로 작성하여 자동으로 Item 객체를 만들어 준다.
    // @ModelAttribute("item") 으로 명시적으로 Model.setAttribute 해줄 수 있으나, 생략할 시 자동으로 클래스 이름으로 생성.
    // @ModelAttribute 자체도 생략이 가능하지만... 왠만하면 명시적으로 적어주는 것이 직관적이고 좋을 것 같다.
    @PostMapping("/add")
    fun saveV2(@ModelAttribute item : Item, rda : RedirectAttributes, model : Model): String{

        val errorCodes = HashMap<String, String>()

        if (item.itemName.isBlank()) {
            errorCodes["itemName"] = "상품 이름을 입력해주세요."
        }
        if (item.price < 1000 || item.price > 1000000 ){
            errorCodes["itemPrice"] = "상품 가격은 1,000원 이상, 1,000,000 원 이하까지 허용 됩니다."
        }
        if (item.quantity < 1 || item.quantity > 9999) {
            errorCodes["itemQuantity"] = "상품 수량은 1개 이상, 9999개 이하까지 허용 됩니다."
        }
        if (item.price * item.quantity < 10000) {
            errorCodes["globalError"] = "상품 가격 x 상품 수량은 10,000 이상이어야 합니다. 현재 값 = ${item.price * item.quantity}"
        }

        if (errorCodes.isNotEmpty()) {
            logger.info { "errorCodes = ${errorCodes.entries}" }
            model.addAttribute("errorCodes", errorCodes)
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