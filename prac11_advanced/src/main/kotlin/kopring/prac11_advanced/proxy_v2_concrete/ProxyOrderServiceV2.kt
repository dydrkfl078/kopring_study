package kopring.prac11_advanced.proxy_v2_concrete

open class ProxyOrderServiceV2(private val orderRepo : ProxyOrderRepositoryV2)
{
    open fun save (itemName: String) {
        orderRepo.save(itemName)
    }
}