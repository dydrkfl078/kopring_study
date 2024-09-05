package kopring.prac11_advanced.proxy_v2_concrete

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/concrete/v2")
@RestController
open class ProxyOrderControllerV2 (
    private val orderService : ProxyOrderServiceV2
){

    @GetMapping("/request")
    open fun request(itemName: String): String {
        orderService.save(itemName)

        return "$itemName ok"
    }
}