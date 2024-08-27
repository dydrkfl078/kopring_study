package kopring.prac11_advanced.v0

import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryV0 {

    companion object {
        const val ERROR_NAME = "예외"
    }

    fun save (itemName: String) {

        if (itemName == ERROR_NAME) {
            throw IllegalArgumentException("예외 발생")
        }

        Thread.sleep(1000L)
    }
}