package kopring.prac11_advanced.proxy_v3_jdk_dynamic

class ProxyOrderRepositoryImplV3 : ProxyOrderRepositoryV3{

    companion object {
        const val ERROR_NAME = "예외"
    }

    override fun save(itemName: String) {
        if (itemName == ERROR_NAME) {
            throw IllegalArgumentException("예외 발생")
        }

        Thread.sleep(3000L)
    }
}