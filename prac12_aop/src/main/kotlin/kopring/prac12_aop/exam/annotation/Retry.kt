package kopring.prac12_aop.exam.annotation

// value == 재시도 할 횟수. 기본 값 3 회.

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Retry(val value : Int = 3) {
}
