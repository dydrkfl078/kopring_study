package kopring.prac11_advanced.proxy_v3_jdk_dynamic

import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

import io.github.oshai.kotlinlogging.KotlinLogging
private val logger = KotlinLogging.logger {  }
class ProxyOrderControllerImplV3 (
    private val orderService : ProxyOrderServiceV3
): ProxyOrderControllerV3{


    override fun request(@RequestParam itemName: String): String {
        try {
            orderService.save(itemName)
        } catch (e: ClassCastException) {
            logger.info { "${e.message}, ${e.cause}" }
        }

        return "$itemName ok!"
    }
}