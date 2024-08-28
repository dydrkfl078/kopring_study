package kopring.prac11_advanced.v3

import kopring.prac11_advanced.trace.TraceStatus
import kopring.prac11_advanced.trace.logger.FieldLogTrace
import kopring.prac11_advanced.trace.logger.TraceLogger
import kopring.prac11_advanced.trace.logger.TraceLoggerV2
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v3")
class OrderControllerV3 (
    private val logger : TraceLogger,
    private val orderService : OrderServiceV3){

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