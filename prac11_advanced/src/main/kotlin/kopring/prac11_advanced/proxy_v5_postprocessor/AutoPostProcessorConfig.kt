package kopring.prac11_advanced.proxy_v5_postprocessor

import kopring.prac11_advanced.proxy_v4_cglib.TraceLoggerAdvice
import kopring.prac11_advanced.trace.logger.TraceLogger
import org.springframework.aop.Advisor
import org.springframework.aop.Pointcut
import org.springframework.aop.aspectj.AspectJExpressionPointcut
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.aop.support.NameMatchMethodPointcut
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy

// Bean 에 pointcut advisor 등록 하면 자동으로 Spring PostProcessor 가 적용이 된다.
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
class AutoPostProcessorConfig {

//    @Bean
    fun logTracePostProcessor(trace: TraceLogger): Advisor {
        val pointcut = NameMatchMethodPointcut().apply {
            setMappedNames("requestProxy","orderProxy","saveProxy")
        }
        return DefaultPointcutAdvisor(pointcut, TraceLoggerAdvice(trace))
    }

    @Bean
    fun logTracePostProcessorAspectJ(trace: TraceLogger): Advisor {
        val basePath = "kopring.prac11_advanced.proxy_v5_postprocessor.."
        val pointcut = AspectJExpressionPointcut().apply {

            expression = "execution(* $basePath*(..)) && !execution(* ${basePath}noLog(..))"
        }
        return DefaultPointcutAdvisor(pointcut, TraceLoggerAdvice(trace))
    }
}