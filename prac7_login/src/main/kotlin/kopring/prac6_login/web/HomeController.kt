package kopring.prac6_login.web

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.http.HttpServletRequest
import kopring.prac6_login.domain.member.Member
import kopring.prac6_login.domain.member.MemberRepo
import kopring.prac6_login.web.argument_resolver.Login
import kopring.prac6_login.web.session.SessionConst
import kopring.prac6_login.web.session.SessionManager
import kopring.prac6_login.web.session.SessionManager.Companion.SESSION_ID
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.support.SessionStatus

private val logger = KotlinLogging.logger {  }

@Controller
class HomeController (private val memberRepo : MemberRepo, private val sessionManager: SessionManager) {

//    @GetMapping("/home")
    fun home(@CookieValue(SESSION_ID, required = false) sessionKey : String?, model: Model):String {

        sessionKey ?: return "home"
        val sessionValue = sessionManager.getSession(sessionKey) as Long? ?: return "home"
        val loginMember = memberRepo.findById(sessionValue) ?: return "home"

        model.addAttribute("member",loginMember)

        return "loginHome"
    }

    // Servlet Http Session 적용.
//    @GetMapping("/home")
    fun homeV2(request : HttpServletRequest, model: Model):String {

        val session = request.getSession(false) ?: return "home"

        val loginMember = session.getAttribute(SessionConst.LOGIN_MEMBER) as Member? ?: return "home"

        model.addAttribute("member",loginMember)

        return "loginHome"
    }

    // Spring, Servlet http session 통합
    @GetMapping("/home")
    fun homeV3(@Login loginMember : Member?, model: Model):String {

        model.addAttribute("member",loginMember ?: return "home")

        return "loginHome"
    }

    @PostMapping("/logout")
    fun logout(request: HttpServletRequest): String {

        // 1. Custom session 적용
        // sessionManager.expireCookie(response, SESSION_ID)

        // 2. Servlet http session 적용
        request.getSession(false)?.invalidate()

        return "redirect:/home"
    }

}