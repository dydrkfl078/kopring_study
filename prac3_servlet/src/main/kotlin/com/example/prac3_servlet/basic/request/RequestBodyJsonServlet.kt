package com.example.prac3_servlet.basic.request

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.util.StreamUtils

@WebServlet (name = "requestBodyJsonServlet", urlPatterns = ["/request-body-json"])
class RequestBodyJsonServlet : HttpServlet() {

    private val objectMapper = jacksonObjectMapper()

    override fun service(req: HttpServletRequest?, resp: HttpServletResponse?) {

        val inputStream = req!!.inputStream
        val messageBody = StreamUtils.copyToString(inputStream, charset("UTF-8"))

        val helloData: HelloData = objectMapper.readValue(messageBody, HelloData::class.java)
        println("helloData.userName = ${helloData.userName}")
        println("helloData.age = ${helloData.age}")

        resp!!.writer.write("OK")
    }
}