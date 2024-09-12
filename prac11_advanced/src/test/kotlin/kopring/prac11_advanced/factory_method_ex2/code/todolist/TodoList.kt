package kopring.prac11_advanced.factory_method_ex2.code.todolist

import kopring.prac11_advanced.factory_method_ex2.code.category.Category
import kopring.prac11_advanced.factory_method_ex2.code.factory.todo_task.ConcreteTaskFactory
import io.github.oshai.kotlinlogging.KotlinLogging
import kopring.prac11_advanced.factory_method_ex2.CategoryList
import kopring.prac11_advanced.factory_method_ex2.CategoryList.*
import kopring.prac11_advanced.factory_method_ex2.code.category.Title
import kopring.prac11_advanced.factory_method_ex2.code.factory.category.CalendarFactory
import kopring.prac11_advanced.factory_method_ex2.code.factory.category.CategoryFactory
import kopring.prac11_advanced.factory_method_ex2.code.factory.category.DropdownMenuFactory
import kopring.prac11_advanced.factory_method_ex2.code.factory.category.TextMemoFactory

private val logger = KotlinLogging.logger {  }
class TodoList {

    private val categories = mutableListOf<Category>(Title())
    private val taskList = mutableListOf<Task>()

    fun addCategory(categoryFactory : CategoryFactory) {
        val category = categoryFactory.addCategory()

        categories.add(category)
    }

    fun addTask() {

        // category 는 list 로 넘겨주기 때문에 어차피 똑같은 Category 리스트를 참조하고 있다.
        // 위 와 같은 상황으로, category 목록이 적용된 task 를 만드려고 Factory method 를 사용할 필요는 없다.
        // val task = ConcreteTaskFactory().createTask(categories)

        val task = Task(categories)

        taskList.add(task)
        logger.info { "TodoList 할 일 추가 완료." }
    }

    fun getTasks(): List<Task> {
        return taskList
    }
}