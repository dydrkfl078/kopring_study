package kopring.prac11_advanced.proxy_v3_jdk_dynamic

class ProxyOrderServiceImplV3(
    private val orderRepo: ProxyOrderRepositoryV3
): ProxyOrderServiceV3 {

    override fun save(itemName: String) {
        orderRepo.save(itemName)
    }
}