package kopring.prac11_advanced.factory_method_ex2.code.factory.todo_task

import kopring.prac11_advanced.factory_method_ex2.code.todolist.Task
import kopring.prac11_advanced.factory_method_ex2.code.category.Category

class ConcreteTaskFactory : TaskFactory() {

    override fun addCategory(todoCategories: MutableList<Category>): Task {
        return Task(todoCategories)
    }
}