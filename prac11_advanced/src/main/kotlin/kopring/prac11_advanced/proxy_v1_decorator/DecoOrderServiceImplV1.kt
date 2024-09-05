package kopring.prac11_advanced.proxy_v1_decorator

class DecoOrderServiceImplV1(private val orderRepo : DecoOrderRepositoryV1): DecoOrderServiceV1
{

    override fun orderItem(itemName: String) {
        orderRepo.save(itemName)
    }
}