package kopring.prac6_login.domain.login

import kopring.prac6_login.domain.member.Member
import kopring.prac6_login.domain.member.MemberRepo
import org.springframework.stereotype.Service

@Service
class LoginService(private val memberRepo : MemberRepo) {

    fun login(loginId: String, password: String): Member? {
        memberRepo.findByLoginId(loginId)?.let { member ->
            return if (member.password == password) member else null
        }?: return null
    }
}