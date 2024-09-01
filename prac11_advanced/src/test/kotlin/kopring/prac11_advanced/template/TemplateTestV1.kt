package kopring.prac11_advanced.template
import kopring.prac11_advanced.template.code.AbstractTemplate
import kopring.prac11_advanced.template.code.SubClassLogic1
import kopring.prac11_advanced.template.code.SubClassLogic2
import org.junit.jupiter.api.Test

// V1 - 추상 클래스로 변동되는 로직과 변동되지 않는 로직을 분리한다.

class TemplateTestV1 {

    @Test
    fun templateMethodV1(){
        val logic1 : AbstractTemplate = SubClassLogic1()
        val logic2 : AbstractTemplate = SubClassLogic2()

        logic1.execute()
        logic2.execute()
    }
}