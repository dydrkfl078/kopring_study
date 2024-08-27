package kopring.prac11_advanced.v0

import org.springframework.stereotype.Service

@Service
class OrderServiceV0(private val orderRepo : OrderRepositoryV0) {

    fun save (itemName: String) {
        orderRepo.save(itemName)
    }
}