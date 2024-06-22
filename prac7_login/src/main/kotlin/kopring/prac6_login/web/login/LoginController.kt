package kopring.prac6_login.web.login

import io.github.oshai.kotlinlogging.KotlinLogging
import kopring.prac6_login.domain.login.LoginService
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

private val logger = KotlinLogging.logger {  }

@Controller
@RequestMapping("/login")
class LoginController(private val loginService: LoginService) {

    @GetMapping
    fun loginForm (@ModelAttribute("loginForm") form: LoginForm): String {
        return "login/loginForm"
    }

    @PostMapping
    fun login(@Validated @ModelAttribute("loginForm") form: LoginForm, bindingResult: BindingResult):  String {

        if (bindingResult.hasErrors()) {
            logger.info { "errors = $bindingResult" }
            return "login/loginForm"
        }

        val loginMember = loginService.login(form.loginId!!, form.password!!)

        if (loginMember == null) {
            logger.info { "로그인 실패" }
            bindingResult.reject("loginFail","아이디 또는 비밀번호가 올바르지 않습니다.")
            return "login/loginForm"
        }

        // todo 로그인 성공 처리
        return "redirect:/home"
    }
}