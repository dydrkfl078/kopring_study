package com.example.prac3_servlet.web.servlet

import com.example.prac3_servlet.domain.member.Member
import com.example.prac3_servlet.domain.member.MemberRepo
import jakarta.servlet.ServletException
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import jdk.jfr.ContentType
import java.io.IOException
import kotlin.jvm.Throws

@WebServlet (name = "memberSaveServlet", urlPatterns = ["/servlet/members/save"])
class MemberSaveServlet : HttpServlet() {

    private val memberRepo = MemberRepo()

    override fun service(req: HttpServletRequest?, res: HttpServletResponse?) {

        val username = req!!.getParameter("username")
        val age = req!!.getParameter("age")!!.toInt()
        val member = Member(username,age)

        memberRepo.save(member)

        res!!.apply {
            contentType = "text/html"
            characterEncoding = "utf-8"
        }

        println("response Successful")
        val w = res!!.writer
        w.write("<html>\n" +
                "<head>\n" +
                " <meta charset=\"UTF-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "성공\n" +
                "<ul>\n" +
                " <li>id="+member.id+"</li>\n" +
                " <li>username="+member.getName()+"</li>\n" +
                " <li>age="+member.getAge()+"</li>\n" +
                "</ul>\n" +
                "<a href=\"/index.html\">메인</a>\n" +
                "</body>\n" +
                "</html>")



    }
}