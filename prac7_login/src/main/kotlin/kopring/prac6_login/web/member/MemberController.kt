package kopring.prac6_login.web.member

import io.github.oshai.kotlinlogging.KotlinLogging
import kopring.prac6_login.domain.member.Member
import kopring.prac6_login.domain.member.MemberRepo
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

private val logger = KotlinLogging.logger {  }

@Controller
@RequestMapping("/members")
class MemberController(private val memberRepo : MemberRepo) {

    @GetMapping("/add")
    fun addForm(@ModelAttribute("member") member : Member) : String {

        return "members/addMemberForm"
    }

    @PostMapping("/add")
    fun save(@Validated @ModelAttribute member: Member, bindingResult: BindingResult): String{

        if (bindingResult.hasErrors()) {
            logger.info { "errors = $bindingResult" }
            return "members/addMemberForm"
        }

        memberRepo.save(member)
        return "redirect:/home"
    }
}