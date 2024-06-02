package practice_thymeleaf.kopring_prac1.controller

import org.springframework.stereotype.Controller
import practice_thymeleaf.kopring_prac1.service.MemberService

@Controller
class MemberController(private val memberService: MemberService) {

}