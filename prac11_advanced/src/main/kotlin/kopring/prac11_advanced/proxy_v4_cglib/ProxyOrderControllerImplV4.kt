package kopring.prac11_advanced.proxy_v4_cglib

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Primary
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


class ProxyOrderControllerImplV4(
    private val cglibOrderService : ProxyOrderServiceV4)
    : ProxyOrderControllerV4 {

    override fun request(@RequestParam itemName: String): String {

        cglibOrderService.save(itemName)
        return "$itemName ok"
    }
}