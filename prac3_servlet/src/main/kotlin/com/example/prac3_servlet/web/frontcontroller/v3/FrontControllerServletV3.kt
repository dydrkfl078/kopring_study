package com.example.prac3_servlet.web.frontcontroller.v3

import com.example.prac3_servlet.web.frontcontroller.MyView
import com.example.prac3_servlet.web.frontcontroller.v3.controller.MemberFormControllerV3
import com.example.prac3_servlet.web.frontcontroller.v3.controller.MemberListControllerV3
import com.example.prac3_servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet (name = "frontControllerServletV3", urlPatterns = ["/front-controller/v3/*"])
class FrontControllerServletV3 : HttpServlet() {

    private val controllerMap = HashMap<String, ControllerV3>()

    init {
        controllerMap["/front-controller/v3/members/new-form"] = MemberFormControllerV3()
        controllerMap["/front-controller/v3/members/save"] = MemberSaveControllerV3()
        controllerMap["/front-controller/v3/members"] = MemberListControllerV3()
    }

    override fun service(request: HttpServletRequest, response: HttpServletResponse) {

        val requestUrl = request.requestURI
        val controller = controllerMap[requestUrl]

        val paramMap = createParamMap(request)
        val mv = controller?.process(paramMap)


        val viewName = mv!!.getViewName()
        val view = viewResolver(viewName)

        if (controller == null) response.status = HttpServletResponse.SC_NOT_FOUND
        else {
            view.render(mv.getModel(),request, response)}

    }

    private fun viewResolver(viewName: String) = MyView("/WEB-INF/views/${viewName}.jsp")

    private fun createParamMap(request: HttpServletRequest): HashMap<String, String> {
        val paramMap = HashMap<String, String>()

        request.parameterNames.iterator().forEachRemaining {
            paramMap[it] = request.getParameter(it)
        }
        return paramMap

    }
}