package kopring.prac11_advanced.proxy_v2_concrete.proxy

import kopring.prac11_advanced.trace.TraceStatus
import kopring.prac11_advanced.trace.logger.TraceLogger
import kopring.prac11_advanced.proxy_v2_concrete.ProxyOrderServiceV2

class OrderServiceLogProxy (
    private val proxyOrderService : ProxyOrderServiceV2,
    private val trace : TraceLogger,
    private val orderRepositoryLogProxy : OrderRepositoryLogProxy
) : ProxyOrderServiceV2(orderRepositoryLogProxy){

    override fun save(itemName: String) {
        var status : TraceStatus? = null

        try {
            status = trace.begin("Proxy orderService 실행")

            proxyOrderService.save(itemName)

            trace.end(status)

        } catch (e: Exception) {
            trace.exception(status!!,e)
            throw e
        }
    }
}