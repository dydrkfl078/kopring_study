package practice_thymeleaf.kopring_prac1.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import practice_thymeleaf.kopring_prac1.domain.Member
import practice_thymeleaf.kopring_prac1.service.MemberService

@Controller
class HomeController(private val memberService: MemberService) {

    @GetMapping("/")
    fun home():String {
        return "home"
    }

    @GetMapping("/members/new")
    fun index() : String {
        return "signup"
    }

    @PostMapping("/members/new")
    fun join(memberForm: MemberForm): String {
        println("test ${memberForm.name}")

        memberService.join(Member(memberForm.name))

        return "redirect:/"
    }

    @GetMapping("/members")
    fun getMemberList(model : Model): String{
        var members = memberService.findMembers()
        model.addAttribute("members",members)
        return "member_list"
    }
}