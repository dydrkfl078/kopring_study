package kopring.prac11_advanced.proxy_v5_postprocessor

import kopring.prac11_advanced.trace.callback.TemplateLogTrace
import kopring.prac11_advanced.trace.logger.TraceLogger
import org.springframework.stereotype.Service

@Service
class ProxyOrderServiceV5(
    private val orderRepo: ProxyOrderRepositoryV5,
) {
    fun save(itemName: String) {
        orderRepo.save(itemName)

    }
}