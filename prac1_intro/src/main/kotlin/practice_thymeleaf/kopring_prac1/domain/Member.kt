package practice_thymeleaf.kopring_prac1.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Member(var name:String = "") {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id : Long = 0

}