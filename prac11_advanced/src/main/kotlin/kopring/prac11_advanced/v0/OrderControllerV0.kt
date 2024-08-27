package kopring.prac11_advanced.v0

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

private val logger = KotlinLogging.logger {  }

@RestController
@RequestMapping("/v0")
class OrderControllerV0 (private val orderService : OrderServiceV0){

    @GetMapping("/request")
    fun request(@RequestParam itemName: String): String {
        logger.info { "request" }
        orderService.save(itemName)
        return "$itemName ok"
    }
}