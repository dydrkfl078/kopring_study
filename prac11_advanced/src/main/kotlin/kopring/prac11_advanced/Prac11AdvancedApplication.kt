package kopring.prac11_advanced

import kopring.prac11_advanced.proxy_v2_concrete.ConcreteConfig
import kopring.prac11_advanced.proxy_v3_jdk_dynamic.JdkDynamicConfig
import kopring.prac11_advanced.proxy_v4_cglib.CglibDynamicConfig
import kopring.prac11_advanced.proxy_v6_aspect.AspectConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

//@Import(ConcreteConfig::class)
//@Import(JdkDynamicConfig::class)
//@Import(CglibDynamicConfig::class)
@Import(AspectConfig::class)
@SpringBootApplication(scanBasePackages = [Prac11AdvancedApplication.TEST])
class Prac11AdvancedApplication {
	companion object{
		const val TEST = "kopring.prac11_advanced"
	}
}

fun main(args: Array<String>) {
	runApplication<Prac11AdvancedApplication>(*args)
}
