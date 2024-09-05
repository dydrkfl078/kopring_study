package kopring.prac11_advanced.proxy_v1_decorator

import kopring.prac11_advanced.trace.TraceStatus
import kopring.prac11_advanced.trace.logger.TraceLogger
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

// 컨트롤러는 데코레이터 생략...
@RestController
@RequestMapping("/deco/v1")
class DecoOrderControllerV1 (
    private val logger : TraceLogger,
    private val orderService : DecoOrderServiceV1
){

    @GetMapping("/request")
    fun request(@RequestParam itemName: String): String {
        var status : TraceStatus? = null

        try {
            status = logger.begin("OrderController.request()")
            orderService.orderItem(itemName)
            logger.end(status)
            return "$itemName ok"
        } catch (e: Exception) {
            logger.exception(status!!,e)
            throw e
        }
    }
}