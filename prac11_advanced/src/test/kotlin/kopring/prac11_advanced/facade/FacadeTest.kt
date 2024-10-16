package kopring.prac11_advanced.facade

import kopring.prac11_advanced.facade.code.FacadeOrder
import kopring.prac11_advanced.facade.code.FacadeOrderMessage
import kopring.prac11_advanced.facade.code.FacadeOrderService
import kopring.prac11_advanced.facade.code.FacadePurchase
import org.junit.jupiter.api.Test


class FacadeTest {

    private val order = FacadeOrder()
    private val purchase = FacadePurchase()
    private val orderMessage = FacadeOrderMessage()

    private val orderService = FacadeOrderService(order,purchase,orderMessage)

    @Test
    fun newOrder() {
        orderService.startOrderProcess()
    }
}

//Facade 패턴을 적용했을 때, 단순히 코드가 더 복잡해져 보일 수 있다.
// 하지만, 일반적인 웹, 앱 애플리케이션 서비스의 경우 하나의 화면에는 다양한 기능들로 구성되어져 있다.
// 때문에 클라이언트 단 ( 예시 코드의 main 함수 ) 의 코드가 더 많아지게 된다.
//
//클라이언트 단 내부에서 여러 클래스 객체의 상호작용을 제어하는 경우, 비동기 관리 등 객체들의 상호작용에 대한 제어의 책임이 생기게 된다.
//
//즉, 단일 책임 원칙을 위반하면서 클라이언트 단의 코드가 비대지므로 유지보수성 저하, 코드의 가독성 저하 등의 문제를 초래한다.