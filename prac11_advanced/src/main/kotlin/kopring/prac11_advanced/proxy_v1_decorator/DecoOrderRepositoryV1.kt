package kopring.prac11_advanced.proxy_v1_decorator

interface DecoOrderRepositoryV1 {
    companion object {
        const val ERROR_NAME = "예외"
    }

    fun save(itemName: String)
}