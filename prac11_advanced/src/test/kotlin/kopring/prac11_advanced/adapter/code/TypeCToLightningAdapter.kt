package kopring.prac11_advanced.adapter.code

// 위임을 통한 Adapter 패턴
class TypeCToLightningAdapter(private val chargerTypeC: ChargerTypeC) :
    ChargerTypeLightning {

    override fun connectionLightning() {
        chargerTypeC.connection()
        println("C to lightning Adapter 연결")
        println("Lightning 타입 USB 를 통해 전류가 흐른다.")
    }
}