package kopring.prac11_advanced.proxy_v3_jdk_dynamic

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
interface ProxyOrderControllerV3 {

    @GetMapping("/jdk/v3/request")
    fun request(itemName : String): String
}