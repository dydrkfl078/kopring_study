package kopring.prac11_advanced.proxy_v5_postprocessor

import kopring.prac11_advanced.trace.callback.TemplateLogTrace
import kopring.prac11_advanced.trace.logger.TraceLogger
import org.springframework.stereotype.Repository

@Repository
class ProxyOrderRepositoryV5(
) {

    companion object {
        const val ERROR_NAME = "예외"
    }

    fun save(itemName: String) {
        if (itemName.contains(ERROR_NAME)) {
            throw IllegalArgumentException("예외 발생")
        }

        Thread.sleep(3000L)
    }
}