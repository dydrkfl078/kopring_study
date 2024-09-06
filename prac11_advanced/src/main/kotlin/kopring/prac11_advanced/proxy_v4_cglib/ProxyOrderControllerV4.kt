package kopring.prac11_advanced.proxy_v4_cglib

import org.springframework.context.annotation.Primary
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
interface ProxyOrderControllerV4 {

    @GetMapping("/cglib/v4/request")
    fun request(itemName: String): String
}