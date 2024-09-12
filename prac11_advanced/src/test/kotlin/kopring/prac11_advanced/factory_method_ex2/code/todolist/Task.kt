package kopring.prac11_advanced.factory_method_ex2.code.todolist


import io.github.oshai.kotlinlogging.KotlinLogging
import kopring.prac11_advanced.factory_method_ex2.code.category.Category

private val logger = KotlinLogging.logger {  }
class Task(private val categories: MutableList<Category>) {

    fun displayCategories() {

        categories.forEach {
            logger.info { "할 일 카테고리 - ${it::class.simpleName}" }
        }
    }
}