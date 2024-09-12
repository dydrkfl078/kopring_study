package kopring.prac11_advanced.factory_method_ex2.code.factory.category

import kopring.prac11_advanced.factory_method_ex2.code.category.Category

import io.github.oshai.kotlinlogging.KotlinLogging
private val logger = KotlinLogging.logger {  }
abstract class CategoryFactory {

    fun addCategory() : Category {

        val category = createCategory()
        logger.info { "작업 목록 추가 - [ 새로운 todoList 카테고리 생성 ] " }

        return category
    }

    protected abstract fun createCategory(): Category
}