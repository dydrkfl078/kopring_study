package kopring_prac.prac8_error.exception.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST, reason = "잘못된 요청 입니다.")
class BadRequestException : RuntimeException() {
}