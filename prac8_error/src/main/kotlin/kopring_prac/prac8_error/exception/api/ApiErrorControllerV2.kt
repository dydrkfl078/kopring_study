package kopring_prac.prac8_error.exception.api

import io.github.oshai.kotlinlogging.KotlinLogging
import kopring_prac.prac8_error.exception.exception.BadRequestException
import kopring_prac.prac8_error.exception.exception.UserException
import kopring_prac.prac8_error.exception.exception.exception_handler.ErrorResult
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

private val logger = KotlinLogging.logger {  }

@RestController
class ApiErrorControllerV2 {


    // ExceptionHandler 로직은 ApiErrorControllerV2ExHandler 로 분리.

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(IllegalArgumentException::class)
//    fun exHandler1(e : IllegalArgumentException): ErrorResult {
//        logger.error { "[exceptionHandler = $e]" }
//        return ErrorResult("잘못된 접근 param = bad",e.message)
//    }
//
//    @ExceptionHandler
//    fun exHandler2ResponseEntity(e : UserException ): ResponseEntity<ErrorResult> {
//        logger.error { "[exceptionHandler = $e]" }
//        val errorResult = ErrorResult("ResponseEntityError", e.message)
//        return ResponseEntity(errorResult, HttpStatus.BAD_REQUEST)
//    }
//
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler
//    fun internalErrorCode(e: Exception) : ErrorResult {
//        logger.error { "Internal Error = $e" }
//        return ErrorResult("Internal Error 발생.", e.message)
//    }

    @GetMapping("/api2/members/{id}")
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

    @GetMapping("api2/response-status/ex1")
    fun responseStatus(){
        throw BadRequestException()
    }
}