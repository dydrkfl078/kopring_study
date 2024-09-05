package kopring.prac11_advanced.proxy_v2_concrete.proxy

import kopring.prac11_advanced.trace.TraceStatus
import kopring.prac11_advanced.trace.logger.TraceLogger
import kopring.prac11_advanced.proxy_v2_concrete.ProxyOrderRepositoryV2

class OrderRepositoryLogProxy(
    private val trace: TraceLogger,
    private val proxyOrderRepository: ProxyOrderRepositoryV2): ProxyOrderRepositoryV2() {

    override fun save(itemName: String) {

        var status : TraceStatus? = null

        try {
            status = trace.begin("Proxy orderRepository 실행")

            proxyOrderRepository.save(itemName)

            trace.end(status)

        } catch (e: Exception) {
            trace.exception(status!!,e)
            throw e

        }
    }
}