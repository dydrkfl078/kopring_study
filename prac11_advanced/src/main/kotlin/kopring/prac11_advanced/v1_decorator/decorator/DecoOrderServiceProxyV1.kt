package kopring.prac11_advanced.v1_decorator.decorator

import kopring.prac11_advanced.trace.TraceStatus
import kopring.prac11_advanced.trace.logger.TraceLogger
import kopring.prac11_advanced.v1_decorator.DecoOrderServiceV1

class DecoOrderServiceProxyV1(
    private val target : DecoOrderServiceV1,
    private val trace : TraceLogger
): DecoOrderServiceV1 {

    override fun orderItem(itemName: String) {

        var status : TraceStatus? = null

        try {
            status = trace.begin("DecoOrderServiceProxyV1")

            target.orderItem(itemName)

            trace.end(status)

        } catch (e: Exception) {
            trace.exception(status!!, e)
            throw e
        }
    }
}