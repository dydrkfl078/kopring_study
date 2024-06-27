package kopring_prac.prac8_error

import jakarta.servlet.DispatcherType
import jakarta.servlet.Filter
import kopring_prac.prac8_error.exception.filter.LogFilter
import kopring_prac.prac8_error.exception.interceptor.LogInterceptor
import kopring_prac.prac8_error.exception.resolver.MyHandlerException
import kopring_prac.prac8_error.exception.resolver.UserHandlerExceptionResolver
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.HandlerExceptionResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {

    override fun extendHandlerExceptionResolvers(resolvers: MutableList<HandlerExceptionResolver>) {
        resolvers.add(MyHandlerException())
        resolvers.add(UserHandlerExceptionResolver())
    }


    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(LogInterceptor())
            .order(1)
            .addPathPatterns("/**")
            .excludePathPatterns("/css/**", "*.ico","/error","/error-page/**")
    }

    //    @Bean
    fun logFilter(): FilterRegistrationBean<Filter>{
        return FilterRegistrationBean<Filter>().apply {
            filter = LogFilter()
            order = 1
            addUrlPatterns("/*")
            setDispatcherTypes(DispatcherType.REQUEST,DispatcherType.ERROR)
        }
    }
}