package kopring_prac.prac8_error.exception.api

import io.github.oshai.kotlinlogging.KotlinLogging
import kopring_prac.prac8_error.exception.exception.BadRequestException
import kopring_prac.prac8_error.exception.exception.UserException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

private val logger = KotlinLogging.logger {  }

@RestController
class ApiErrorController {

    @GetMapping("/api/members/{id}")
    fun responseDto(@PathVariable("id") id: String): TestDto {

        if (id == "ex") {
            throw RuntimeException("잘못된 사용자 입니다.")
        }

        if (id == "bad") {
            throw IllegalArgumentException("잘못된 사용자 입니다.")
        }

        if (id == "user-ex") {
            throw UserException(message = "잘못된 접근 입니다.")
        }

        return TestDto(id, "test name")
    }

    @GetMapping("api/response-status/ex1")
    fun responseStatus(){
        throw BadRequestException()
    }
}