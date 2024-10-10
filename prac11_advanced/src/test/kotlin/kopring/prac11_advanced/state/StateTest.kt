package kopring.prac11_advanced.state

import org.junit.jupiter.api.Test
import kopring.prac11_advanced.state.code.Category
import kopring.prac11_advanced.state.code.Number
import kopring.prac11_advanced.state.code.Text

class StateTest {

    private val task = StatePatternTask()

    @Test
    fun stateTest() {
        task.write("1텍2스3트4")
        task.setCategoryType(Number())
        task.write("1텍2스3트4")
    }
}

private class StatePatternTask(private var categoryType: Category = Text()) {

    fun setCategoryType(categoryType: Category) {
        this.categoryType = categoryType
    }

    fun write(content: String) {
        categoryType.write(content)
    }
}