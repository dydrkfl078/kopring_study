package kopring.prac12_aop.order.aop

import org.aspectj.lang.annotation.Pointcut

// 포인트 컷을 조합할 수 있다.
class Pointcuts {

    // Class 명이 ~~Service 일 경우를 지정.
    @Pointcut("execution(* *..*Service.*(..))")
    fun allService(){}

    @Pointcut("execution(* *..*Order*.*(..))")
    fun allOrder(){}

    // 여러 포인트 컷을 하나로 묶은 포인트 컷 생성 가능 → 구현 포인트 컷이 public 이어야 한다.
    @Pointcut("allService() && allOrder()")
    fun orderAndService(){}
}