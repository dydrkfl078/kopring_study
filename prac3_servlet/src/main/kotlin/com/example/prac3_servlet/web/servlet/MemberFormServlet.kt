package com.example.prac3_servlet.web.servlet

import com.example.prac3_servlet.domain.member.MemberRepo
import jakarta.servlet.ServletException
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import java.io.IOException
import kotlin.jvm.Throws

@WebServlet (name = "MemberFormServlet", urlPatterns = ["/servlet/members/new-form"])
class MemberFormServlet : HttpServlet(){

    private val memberRepo = MemberRepo()

    @Throws (ServletException::class, IOException::class)
    override fun service(request: HttpServletRequest?, response: HttpServletResponse?) {

        response?.apply {
            contentType = "text/html"
            characterEncoding = "UTF-8"
        }

        val w = response?.writer
        w?.write("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                " <meta charset=\"UTF-8\">\n" +
                " <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form action=\"/servlet/members/save\" method=\"post\">\n" +
                " username: <input type=\"text\" name=\"username\" />\n" +
                " age: <input type=\"text\" name=\"age\" />\n" +
                " <button type=\"submit\">전송</button>\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>\n")
    }
}