package kopring.prac11_advanced.v1

import io.github.oshai.kotlinlogging.KotlinLogging
import kopring.prac11_advanced.trace.TraceStatus
import kopring.prac11_advanced.trace.logger.TraceLoggerV1
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1")
class OrderControllerV1 (
    private val logger : TraceLoggerV1,
    private val orderService : OrderServiceV1){

    @GetMapping("/request")
    fun request(@RequestParam itemName: String): String {
        var status : TraceStatus? = null

        try {
            status = logger.begin("OrderController.request()")
            orderService.save(itemName)
            logger.end(status)
            return "$itemName ok"
        } catch (e: Exception) {
            logger.exception(status!!,e)
            throw e
        }
    }
}