package kopring.prac10_upload.controller

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.util.StreamUtils
import org.springframework.util.StringUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import java.nio.charset.StandardCharsets
import kotlin.math.log

private val logger = KotlinLogging.logger {  }

@Controller
@RequestMapping("/servlet/v2")
class ServletUploadControllerV2 {

    @Value("\${file.dir}")
    private lateinit var fileDir : String

    @GetMapping("/upload")
    fun newFile(): String {
        logger.info { "file dir = [$fileDir]" }
        return "upload-form"
    }

    @PostMapping("/upload")
    fun uploadFile( request : HttpServletRequest): String{
        logger.info { "request = [$request]" }

        val itemName = request.getParameter("itemName")
        logger.info { "itemName = [$itemName]" }

        val parts = request.parts
        logger.info { "parts = [${parts.toList()}]" }

        parts.asSequence().forEach { part ->
            logger.info { "==== PART ====" }
            logger.info { "part name = [${part.name}]" }
            part.headerNames.forEach { header ->
                logger.info { "header name = [${header}] header = [${part.getHeader(header)}]" }
            }

            // 편의 메서드
            // content-disposition = fileName
            logger.info { "submitted file name = [${part.submittedFileName}]" }
            logger.info { "size = [${part.size}]" }

            // 데이터 읽기
            val body = StreamUtils.copyToString(part.inputStream, StandardCharsets.UTF_8)
            logger.info { "body [$body]" }

            // 파일 저장하기
            if (StringUtils.hasText(part.submittedFileName)) {
                val pullPath = fileDir + part.submittedFileName
                logger.info { "파일 저장 pull path = [$pullPath]" }
                part.write(pullPath)
            }
        }
        return "upload-form"
    }
}