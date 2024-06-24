package kopring.prac6_login.web.argument_resolver

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.http.HttpServletRequest
import kopring.prac6_login.domain.member.Member
import kopring.prac6_login.web.session.SessionConst
import org.springframework.core.MethodParameter
import org.springframework.core.io.support.SpringFactoriesLoader.ArgumentResolver
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

private val logger = KotlinLogging.logger {  }

class LoginArgumentResolver : HandlerMethodArgumentResolver{
    override fun supportsParameter(parameter: MethodParameter): Boolean {

        val hasParam : Boolean  =  parameter.hasParameterAnnotation(Login::class.java)
        val hasMemberType : Boolean = Member::class.java.isAssignableFrom(parameter.parameterType)

        return hasParam && hasMemberType
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        val request = webRequest.nativeRequest as HttpServletRequest
        val session = request.session ?: return null

        return session.getAttribute(SessionConst.LOGIN_MEMBER)
    }
}