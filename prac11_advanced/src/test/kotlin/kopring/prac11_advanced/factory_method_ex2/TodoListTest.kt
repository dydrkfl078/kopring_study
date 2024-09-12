package kopring.prac11_advanced.factory_method_ex2

import kopring.prac11_advanced.factory_method_ex2.code.factory.category.CalendarFactory
import kopring.prac11_advanced.factory_method_ex2.code.factory.category.DropdownMenuFactory
import kopring.prac11_advanced.factory_method_ex2.code.factory.category.TextMemoFactory
import kopring.prac11_advanced.factory_method_ex2.code.todolist.TodoList
import org.junit.jupiter.api.Test

class TodoListTest {

    @Test
    fun todoListTest() {
        val studyTodoList = TodoList()

        studyTodoList.addCategory(CalendarFactory())
        studyTodoList.addCategory(DropdownMenuFactory())

        studyTodoList.addTask()
        studyTodoList.addTask()

        val testA = studyTodoList.getTasks()
        testA.forEach {
            it.displayCategories()
        }

        studyTodoList.addCategory(TextMemoFactory())

        val testB = studyTodoList.getTasks()
        testB.forEach {
            it.displayCategories()
        }
    }
}