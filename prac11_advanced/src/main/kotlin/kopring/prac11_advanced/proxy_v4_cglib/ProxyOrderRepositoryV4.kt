package kopring.prac11_advanced.proxy_v4_cglib

import kopring.prac11_advanced.trace.logger.TraceLogger
import kopring.prac11_advanced.trace.template.AbstractLogTrace
import org.springframework.stereotype.Repository

open class ProxyOrderRepositoryV4(
) {

    companion object {
        const val ERROR_NAME = "예외"
    }

    open fun save(itemName: String) {

        if (itemName.contains(ERROR_NAME)) {
            throw IllegalArgumentException("예외 발생")
        }

        Thread.sleep(3000L)
    }
}