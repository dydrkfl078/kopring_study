package kopring.prac10_upload.controller

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

private val logger = KotlinLogging.logger {  }

@Controller
@RequestMapping("/servlet/v1")
class ServletUploadControllerV1 {

    @GetMapping("/upload")
    fun newFile(): String {
        return "upload-form"
    }

    @PostMapping("/upload")
    fun uploadFile( request : HttpServletRequest ): String{
        logger.info { "request = [$request]" }

        val itemName = request.getParameter("itemName")
        logger.info { "itemName = [$itemName]" }

        val parts = request.parts
        logger.info { "parts = [${parts.toList()}]" }

        return "upload-form"
    }
}