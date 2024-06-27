package kopring_prac.prac8_error.exception.exception


class UserException(message: String? = "User Exception",
                    cause: Throwable? = null , enableSuppression: Boolean = false, writableStackTrace: Boolean = true) :
    RuntimeException(message, cause, enableSuppression, writableStackTrace) {

}