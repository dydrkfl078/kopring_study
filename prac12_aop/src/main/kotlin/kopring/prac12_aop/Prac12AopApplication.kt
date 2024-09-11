package kopring.prac12_aop

import kopring.prac12_aop.order.aop.AspectV1
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@Import(AspectV1::class)
@SpringBootApplication
class Prac12AopApplication

fun main(args: Array<String>) {
    runApplication<Prac12AopApplication>(*args)
}
