package kopring.prac11_advanced.proxy_v1_decorator.decorator

import kopring.prac11_advanced.trace.TraceStatus
import kopring.prac11_advanced.trace.logger.TraceLogger
import kopring.prac11_advanced.proxy_v1_decorator.DecoOrderRepositoryV1

class DecoOrderRepositoryProxyV1(
    private val target : DecoOrderRepositoryV1,
    private val trace : TraceLogger
) : DecoOrderRepositoryV1 {

    override fun save(itemName : String) {

        var status : TraceStatus? = null

        try {
            status = trace.begin("DecoOrderRepositoryProxyV1")

            target.save(itemName)

            trace.end(status)

        } catch (e: Exception) {
            trace.exception(status!!, e)
            throw e
        }
    }
}