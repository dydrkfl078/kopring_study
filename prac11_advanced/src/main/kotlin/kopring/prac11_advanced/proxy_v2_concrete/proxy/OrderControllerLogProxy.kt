package kopring.prac11_advanced.proxy_v2_concrete.proxy

import kopring.prac11_advanced.trace.TraceStatus
import kopring.prac11_advanced.trace.logger.TraceLogger
import kopring.prac11_advanced.proxy_v2_concrete.ProxyOrderControllerV2
import kopring.prac11_advanced.proxy_v2_concrete.ProxyOrderServiceV2

class OrderControllerLogProxy(
    private val proxyOrderController: ProxyOrderControllerV2,
    private val orderServiceLogProxy: ProxyOrderServiceV2,
    private val trace: TraceLogger
): ProxyOrderControllerV2(orderServiceLogProxy) {

    override fun request(itemName: String): String {
        var status : TraceStatus? = null

        try {
            status = trace.begin("OrderController.request()")
            proxyOrderController.request(itemName)
            trace.end(status)
            return "$itemName ok"
        } catch (e: Exception) {
            trace.exception(status!!,e)
            throw e
        }
    }
}