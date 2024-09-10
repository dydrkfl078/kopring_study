package kopring.prac11_advanced.adapter.code

// 상속과 구현을 통한 Adapter 패턴
class TypeCToLightningAdapterV2 : ChargerTypeLightning, ChargerTypeC() {

    override fun connectionLightning() {
        connection()
        println("C to lightning Adapter 연결")
    }
}