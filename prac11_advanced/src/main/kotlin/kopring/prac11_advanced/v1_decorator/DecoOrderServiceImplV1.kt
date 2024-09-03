package kopring.prac11_advanced.v1_decorator

import kopring.prac11_advanced.trace.TraceStatus
import kopring.prac11_advanced.trace.logger.TraceLoggerV1
import kopring.prac11_advanced.v1_decorator.DecoOrderRepositoryImplV1
import org.springframework.stereotype.Service

class DecoOrderServiceImplV1(private val orderRepo : DecoOrderRepositoryV1): DecoOrderServiceV1
{

    override fun orderItem(itemName: String) {
        orderRepo.save(itemName)
    }
}