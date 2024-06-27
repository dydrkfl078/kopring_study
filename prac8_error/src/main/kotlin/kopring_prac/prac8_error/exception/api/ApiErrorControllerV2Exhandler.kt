package kopring_prac.prac8_error.exception.api

import io.github.oshai.kotlinlogging.KotlinLogging
import kopring_prac.prac8_error.exception.exception.UserException
import kopring_prac.prac8_error.exception.exception.exception_handler.ErrorResult
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

private val logger = KotlinLogging.logger {  }

@RestControllerAdvice(assignableTypes = [ApiErrorControllerV2::class])
class ApiErrorControllerV2Exhandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException::class)
    fun exHandler1(e : IllegalArgumentException): ErrorResult {
        logger.error { "[exceptionHandler = $e]" }
        return ErrorResult("잘못된 접근 param = bad",e.message)
    }

    @ExceptionHandler
    fun exHandler2ResponseEntity(e : UserException): ResponseEntity<ErrorResult> {
        logger.error { "[exceptionHandler = $e]" }
        val errorResult = ErrorResult("ResponseEntityError", e.message)
        return ResponseEntity(errorResult, HttpStatus.BAD_REQUEST)
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    fun internalErrorCode(e: Exception) : ErrorResult {
        logger.error { "Internal Error = $e" }
        return ErrorResult("Internal Error 발생.", e.message)
    }

}