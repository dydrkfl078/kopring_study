package kopring.prac6_login.web.session

import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import java.util.*
import java.util.concurrent.ConcurrentHashMap

@Component
class SessionManager {

    companion object {
        const val SESSION_ID = "sessionId"
        val sessions = ConcurrentHashMap<String, Any>()
    }

    fun createSession(value : Any, response: HttpServletResponse) {

        // session id 를 생성하고 값을 세션에 저장.
        val uuid = "${UUID.randomUUID()}"
        sessions[uuid] = value

        // Cookie 생성
        response.addCookie(Cookie(SESSION_ID, uuid).apply { maxAge = 3600 })
    }

    fun expireCookie(response: HttpServletResponse, cookieName : String) {
        response.addCookie( Cookie(cookieName, null).apply { maxAge = 0 } )
    }

    fun getSession(uuid: String): Any? {
        return sessions[uuid]
    }

}