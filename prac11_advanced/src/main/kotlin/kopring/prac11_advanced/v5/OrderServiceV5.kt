package kopring.prac11_advanced.v5

import kopring.prac11_advanced.trace.callback.TemplateLogTrace
import kopring.prac11_advanced.trace.logger.TraceLogger
import kopring.prac11_advanced.trace.template.AbstractLogTrace
import org.springframework.stereotype.Service

@Service
class OrderServiceV5(private val orderRepo : OrderRepositoryV5,
                     private val logger : TraceLogger,)
{

    private val template = TemplateLogTrace<Unit>(logger)
    fun save (itemName: String) {
        template.execute("OrderServiceV4.save()") {
            orderRepo.save(itemName)
        }
    }
}