package kopring.prac6_login.web.login

import jakarta.validation.constraints.NotBlank

class LoginForm(
    @NotBlank
    val loginId : String?,

    @NotBlank
    val password : String?
) {
}