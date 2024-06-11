package com.example.prac3_servlet.web.frontcontroller.v4

import com.example.prac3_servlet.web.frontcontroller.MyView
import com.example.prac3_servlet.web.frontcontroller.v4.controller.MemberFormControllerV4
import com.example.prac3_servlet.web.frontcontroller.v4.controller.MemberListControllerV4
import com.example.prac3_servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4
import jakarta.servlet.ServletException
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import java.io.IOException

@WebServlet (name = "frontControllerServletV4", urlPatterns = ["/front-controller/v4/*"])
class FrontControllerServletV4 : HttpServlet() {

    private val controllerMap = HashMap<String,ControllerV4>()

    init {
        controllerMap["/front-controller/v4/members/new-form"] = MemberFormControllerV4()
        controllerMap["/front-controller/v4/members/save"] = MemberSaveControllerV4()
        controllerMap["/front-controller/v4/members"] = MemberListControllerV4()
    }

    @Throws(ServletException::class, IOException::class)
    override fun service(request: HttpServletRequest, response: HttpServletResponse) {


        val requestUri = request.requestURI
        val controller = controllerMap[requestUri]

        val paramMap = createParamMap(request)
        val model = HashMap<String, Any>()

        controller?:response.setStatus(HttpServletResponse.SC_BAD_REQUEST)

        val viewName = controller!!.process(paramMap, model)
        val view = MyView(viewResolver(viewName))

        view.render(model, request, response)
    }

    private fun viewResolver(viewName : String): String {
        return "/WEB-INF/views/${viewName}.jsp"
    }

    private fun createParamMap(request: HttpServletRequest) : HashMap<String, String> {
        val tempParamMap = HashMap<String, String>()
        request.parameterNames.iterator().forEachRemaining { tempParamMap[it] = request.getParameter(it)}

        return tempParamMap
    }
}