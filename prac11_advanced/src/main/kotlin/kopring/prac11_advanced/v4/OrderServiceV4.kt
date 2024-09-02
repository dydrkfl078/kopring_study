package kopring.prac11_advanced.v3

import kopring.prac11_advanced.trace.TraceStatus
import kopring.prac11_advanced.trace.logger.TraceLogger
import kopring.prac11_advanced.trace.template.AbstractLogTrace
import org.springframework.lang.Nullable
import org.springframework.stereotype.Service

@Service
class OrderServiceV4(private val orderRepo : OrderRepositoryV4,
                     private val logger : TraceLogger,)
{

    fun save (itemName: String) {
        val template = object : AbstractLogTrace<Unit>(logger){
            override fun call(): Unit {
                orderRepo.save(itemName)
            }
        }

        template.execute("OrderServiceV4.save()")
    }
}