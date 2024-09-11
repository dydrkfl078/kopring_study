package kopring.prac11_advanced.factory_method.code

class BasicIdCardFactory : IdCardFactory() {
    override fun createIdCard(name: String, email: String): IdCard {
        return BasicIdCard(name, email)
    }
}