package kopring.prac11_advanced.factory_method_ex2.code.factory.category

import kopring.prac11_advanced.factory_method_ex2.code.category.Category
import kopring.prac11_advanced.factory_method_ex2.code.category.DropdownMenu

class DropdownMenuFactory : CategoryFactory() {

    override fun createCategory(): Category {
        return DropdownMenu()
    }
}