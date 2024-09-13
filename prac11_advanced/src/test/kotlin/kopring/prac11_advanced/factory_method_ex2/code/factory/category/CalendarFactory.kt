package kopring.prac11_advanced.factory_method_ex2.code.factory.category

import kopring.prac11_advanced.factory_method_ex2.code.category.Calendar
import kopring.prac11_advanced.factory_method_ex2.code.category.Category

object CalendarFactory : CategoryFactory() {
    override fun createCategory(): Category {
        // 사용자가 설정한 형식의 calendar 형식으로 제공
        // ex) 일요일이 각 주에 맨 앞에 있는 달력 or 일요일이 맨 뒤에 있는 달력
        return Calendar(true)
    }
}