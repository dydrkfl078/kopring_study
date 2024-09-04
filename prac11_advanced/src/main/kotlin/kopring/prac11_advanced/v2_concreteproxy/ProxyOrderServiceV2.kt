package kopring.prac11_advanced.v2_concreteproxy

open class ProxyOrderServiceV2(private val orderRepo : ProxyOrderRepositoryV2)
{
    open fun save (itemName: String) {
        orderRepo.save(itemName)
    }
}