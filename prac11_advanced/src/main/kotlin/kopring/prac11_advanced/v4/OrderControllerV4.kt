package kopring.prac11_advanced.v3

import kopring.prac11_advanced.trace.logger.TraceLogger
import kopring.prac11_advanced.trace.template.AbstractLogTrace
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v4")
class OrderControllerV4 (
    private val logger : TraceLogger,
    private val orderService : OrderServiceV4){

    @GetMapping("/request")
    fun request(@RequestParam itemName: String): String {
        val template = object : AbstractLogTrace<String>(logger){
            override fun call(): String {
                orderService.save(itemName)
                return "ok"
            }
        }

        return template.execute("OrderController.request()")
    }
}