package kopring.prac11_advanced.factory_method_ex2.code.factory.todo_task

import kopring.prac11_advanced.factory_method_ex2.code.todolist.Task
import kopring.prac11_advanced.factory_method_ex2.code.category.Category

abstract class TaskFactory {

    fun createTask(todoCategories : MutableList<Category>): Task {

        val task = Task(todoCategories)

        return task
    }

    protected abstract fun addCategory(todoCategories : MutableList<Category>) : Task
}