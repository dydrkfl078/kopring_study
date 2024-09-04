package kopring.prac11_advanced.v2_concreteproxy

open class ProxyOrderRepositoryV2  {

    companion object {
        const val ERROR_NAME = "예외"
    }

    open fun save (itemName: String) {
        if (itemName == ERROR_NAME) {
            throw IllegalArgumentException("예외 발생")
        }

        Thread.sleep(1000L)

    }
}