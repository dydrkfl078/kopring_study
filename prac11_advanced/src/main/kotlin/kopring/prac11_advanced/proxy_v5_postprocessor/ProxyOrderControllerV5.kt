package kopring.prac11_advanced.proxy_v5_postprocessor

import kopring.prac11_advanced.trace.callback.TemplateLogTrace
import kopring.prac11_advanced.trace.logger.TraceLogger
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/proxy/v5")
class ProxyOrderControllerV5(
    private val orderService: ProxyOrderServiceV5
) {

    @GetMapping("/request")
    fun request(@RequestParam itemName: String): String {
        orderService.save(itemName)
        return "$itemName ok"
    }
}