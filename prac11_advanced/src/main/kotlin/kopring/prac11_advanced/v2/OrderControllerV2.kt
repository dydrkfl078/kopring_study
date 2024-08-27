package kopring.prac11_advanced.v2

import kopring.prac11_advanced.trace.TraceStatus
import kopring.prac11_advanced.trace.logger.TraceLoggerV1
import kopring.prac11_advanced.trace.logger.TraceLoggerV2
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v2")
class OrderControllerV2 (
    private val logger : TraceLoggerV2,
    private val orderService : OrderServiceV2){

    @GetMapping("/request")
    fun request(@RequestParam itemName: String): String {
        var status : TraceStatus? = null

        try {
            status = logger.begin("OrderController.request()")
            orderService.save(status.traceId,itemName)
            logger.end(status)
            return "$itemName ok"
        } catch (e: Exception) {
            logger.exception(status!!,e)
            throw e
        }
    }
}