package kopring.prac11_advanced.factory_method.code

class TransportationIdCardFactory: IdCardFactory() {
    override fun createIdCard(name: String, email: String): IdCard {
        return TransportationIdCard(name, email)
    }
}