package com.example.prac3_servlet.web.frontcontroller.v5

import com.example.prac3_servlet.web.frontcontroller.MyView
import com.example.prac3_servlet.web.frontcontroller.v3.controller.MemberFormControllerV3
import com.example.prac3_servlet.web.frontcontroller.v3.controller.MemberListControllerV3
import com.example.prac3_servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3
import com.example.prac3_servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter
import jakarta.servlet.ServletException
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import java.io.IOException

@WebServlet( name = "frontControllerServletV5", urlPatterns = ["/front-controller/v5/*"])
class  FrontControllerServletV5 : HttpServlet() {

    private val handlerMappingMap = HashMap<String, Any>()
    private val handlerAdapters = mutableListOf<MyHandlerAdapter>()

    init {
        initHandlerMappingMap()
        initHandlerAdapters()
    }

    private fun initHandlerMappingMap(){
        handlerMappingMap["/front-controller/v5/v3/members/new-form"] = MemberFormControllerV3()
        handlerMappingMap["/front-controller/v5/v3/members/save"] = MemberSaveControllerV3()
        handlerMappingMap["/front-controller/v5/v3/members"] = MemberListControllerV3()
    }

    private fun initHandlerAdapters(){
        handlerAdapters.add(ControllerV3HandlerAdapter())
    }

    @Throws(ServletException::class, IOException::class)
    override fun service(req: HttpServletRequest, resp: HttpServletResponse) {

        val handler = getHandler(req)?:resp.setStatus(HttpServletResponse.SC_NOT_FOUND)
        val adapter = getHandlerAdapter(handler)?:throw IllegalArgumentException("Handler Adapter Not Found = $handler")
        val mv = adapter.handle(req, resp, handler)

        val viewName = mv.getViewName()
        val view = viewResolver(viewName)

        view.render(mv.getModel(),req, resp)
    }

    private fun getHandler(req: HttpServletRequest): Any? {
        val requestUri = req.requestURI
        return handlerMappingMap[requestUri]
    }

    private fun getHandlerAdapter(handler : Any): MyHandlerAdapter? {
        for ( temp:MyHandlerAdapter in handlerAdapters){
            if (temp.supports(handler)){
                return temp
            }
        }
        return null
    }

    private fun viewResolver(viewName : String) : MyView {
        return MyView("/WEB-INF/views/${viewName}.jsp")
    }
}