package kopring.prac11_advanced.factory_method_ex2.code.factory.category

import kopring.prac11_advanced.factory_method_ex2.code.category.Category
import kopring.prac11_advanced.factory_method_ex2.code.category.DropdownMenu

class DropdownMenuFactory private constructor(): CategoryFactory() {

    // kotlin object 를 사용하는 경우에는 앱 시작 시점에 객체가 초기화 되어 메모리에 등록되는데, by lazy 를 사용하는 companion object 의 경우
    // 객체가 실제로 필요할 때까지 초기화를 지연시킬 수 있다.

    companion object {
        val instance: DropdownMenuFactory by lazy { DropdownMenuFactory() }
    }

    override fun createCategory(): Category {
        return DropdownMenu()
    }
}