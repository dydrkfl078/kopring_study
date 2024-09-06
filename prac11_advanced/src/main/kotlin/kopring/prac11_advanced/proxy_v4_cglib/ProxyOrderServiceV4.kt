package kopring.prac11_advanced.proxy_v4_cglib

import kopring.prac11_advanced.trace.logger.TraceLogger
import kopring.prac11_advanced.trace.template.AbstractLogTrace
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

open class ProxyOrderServiceV4(
    private val cglibOrderRepository : ProxyOrderRepositoryV4
) {
    open fun save(itemName: String) {
        cglibOrderRepository?.save(itemName)
    }
}