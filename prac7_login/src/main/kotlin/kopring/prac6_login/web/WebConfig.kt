package kopring.prac6_login.web

import jakarta.servlet.Filter
import kopring.prac6_login.web.argument_resolver.LoginArgumentResolver
import kopring.prac6_login.web.filter.LogFilter
import kopring.prac6_login.web.filter.LoginCheckFilter
import kopring.prac6_login.web.interceptor.LogInterceptor
import kopring.prac6_login.web.interceptor.LoginCheckInterceptor
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(LoginArgumentResolver())
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(LogInterceptor())
            .order(1)
            .addPathPatterns("/**")
            .excludePathPatterns("/css/**", "/*.ico","/error")

        registry.addInterceptor(LoginCheckInterceptor())
            .order(2)
            .addPathPatterns("/**")
            .excludePathPatterns("/members/add","/login","logout","/css/**","/home")
    }

    @Bean
    fun logFilter(): FilterRegistrationBean<Filter> {
        return FilterRegistrationBean<Filter>().apply {
            filter = LogFilter()
            order = 1
            addUrlPatterns("/*")
        }
    }

//    @Bean
    fun loginCheckFilter(): FilterRegistrationBean<Filter> {
        return FilterRegistrationBean<Filter>().apply {
            filter = LoginCheckFilter()
            order = 2
            addUrlPatterns("/*")
        }
    }
}