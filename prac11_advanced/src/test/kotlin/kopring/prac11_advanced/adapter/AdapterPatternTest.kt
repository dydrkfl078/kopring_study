package kopring.prac11_advanced.adapter

import kopring.prac11_advanced.adapter.code.ChargerTypeC
import kopring.prac11_advanced.adapter.code.MyPhone
import kopring.prac11_advanced.adapter.code.TypeCToLightningAdapter
import kopring.prac11_advanced.adapter.code.TypeCToLightningAdapterV2
import org.junit.jupiter.api.Test

class AdapterPatternTest {

    @Test
    fun adapterPatternTest() {
        val myPhone = MyPhone()

        // 1. 위임을 사용한 어댑터 패턴
        val myCharger = ChargerTypeC()
        val myAdapterCharger = TypeCToLightningAdapter(myCharger)
        myPhone.charged(myAdapterCharger)

        // 2. 상속을 통한 어댑터 패턴
        val myAdapterChargerV2 = TypeCToLightningAdapterV2()
        myPhone.charged(myAdapterChargerV2)
    }
}