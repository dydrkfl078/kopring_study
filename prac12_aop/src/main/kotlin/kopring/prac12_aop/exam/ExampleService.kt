package kopring.prac12_aop.exam

import org.springframework.stereotype.Service

@Service
class ExampleService(private val exampleRepository: ExampleRepository) {

    fun execute(itemId: String) {
        exampleRepository.call(itemId)
    }
}