package com.example.prac3_servlet.web.springmvc.v3

import com.example.prac3_servlet.domain.member.Member
import com.example.prac3_servlet.domain.member.MemberRepo
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("springmvc/v3/members")
class SpringMemberControllerV3 {

    private val memberRepo = MemberRepo()

    @RequestMapping("/new-form")
    fun newForm() : String = "new-form"

    @RequestMapping("/save")
    fun save(@RequestParam("username") username : String, @RequestParam("age")age: Int, model: Model) : String {

        val member = Member(username,age)
        memberRepo.save(member)
        model.addAttribute("member", member)

        return "save-result"
    }

    @RequestMapping
    fun members(model: Model) : String {
        val members = memberRepo.findAll()
        model.addAttribute("members", members)

        return "members"
    }
}