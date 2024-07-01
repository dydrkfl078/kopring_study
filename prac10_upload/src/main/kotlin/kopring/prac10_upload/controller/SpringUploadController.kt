package kopring.prac10_upload.controller

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile
import java.io.File

private val logger = KotlinLogging.logger {  }

@Controller
@RequestMapping("/spring")
class SpringUploadController {

    @Value("\${file.dir}")
    private lateinit var fileDir : String

    @GetMapping("/upload")
    fun newFile(): String {
        return "upload-form"
    }

    @PostMapping("/upload")
    fun uploadFile (
        @RequestParam("itemName") itemName : String,
        @RequestParam("file") file : MultipartFile,
        request : HttpServletRequest
    ): String {
        logger.info { "request = [$request]" }
        logger.info { "itemName = [$itemName]" }
        logger.info { "file = [$file]" }

        if (!file.isEmpty) {
            val pullPath = fileDir + file.originalFilename
            file.transferTo(File(pullPath))
        }

        return "upload-form"
    }
}