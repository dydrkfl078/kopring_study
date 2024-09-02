package kopring.prac11_advanced.v5

import kopring.prac11_advanced.trace.callback.TemplateLogTrace
import kopring.prac11_advanced.trace.logger.TraceLogger
import kopring.prac11_advanced.trace.template.AbstractLogTrace
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v5")
class OrderControllerV5 (
    private val logger : TraceLogger,
    private val orderService : OrderServiceV5){

    private val template: TemplateLogTrace<String> = TemplateLogTrace(logger)
    @GetMapping("/request")
    fun request(@RequestParam itemName: String): String {
        return template.execute("OrderController.request()") {
            orderService.save(itemName)
            return@execute "ok"
        }
    }
}