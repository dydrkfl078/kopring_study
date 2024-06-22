package kopring.prac6_login.domain.member

import jakarta.validation.constraints.NotBlank

class Member(

    var id : Long? = null,

    @NotBlank
    val loginId : String? = "",

    @NotBlank
    val name : String? = "",

    @NotBlank
    val password : String? = ""

) {
}