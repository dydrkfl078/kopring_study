package com.example.prac3_servlet.web.servlet

import com.example.prac3_servlet.domain.member.MemberRepo
import jakarta.servlet.ServletException
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import java.io.IOException

@WebServlet(name= "memberListServlet", urlPatterns = ["/servlet/members"])
class MemberListServlet : HttpServlet() {

    private val memberRepo = MemberRepo()

    @Throws(ServletException::class, IOException::class)
    override fun service(req: HttpServletRequest, resp: HttpServletResponse) {

        val members = memberRepo.findAll()

        resp.apply {
            contentType = "text/html;charset=UTF-8"
        }

        val w = resp.writer
        w.write("<html>");
        w.write("<head>");
        w.write(" <meta charset=\"UTF-8\">");
        w.write(" <title>Title</title>");
        w.write("</head>");
        w.write("<body>");
        w.write("<a href=\"/index.html\">메인</a>");
        w.write("<table>");
        w.write(" <thead>");
        w.write(" <th>id</th>");
        w.write(" <th>username</th>");
        w.write(" <th>age</th>");
        w.write(" </thead>");
        w.write(" <tbody>");
        /*
         w.write(" <tr>");
         w.write(" <td>1</td>");
         w.write(" <td>userA</td>");
         w.write(" <td>10</td>");
         w.write(" </tr>");
        */
        for (member in members) {
            w.write(" <tr>");
            w.write(" <td>" + member.id + "</td>");
            w.write(" <td>" + member.getName() + "</td>");
            w.write(" <td>" + member.getAge() + "</td>");
            w.write(" </tr>");
        }
        w.write(" </tbody>");
        w.write("</table>");
        w.write("</body>");
        w.write("</html>");
    }

}