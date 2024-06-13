package prac.springmvc.basic.requestmapping

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/mapping/users")
class MappingClassController {

    @GetMapping
    fun getUsers(): String {
        return "get Users"
    }

    @GetMapping("{userId}")
    fun getUser(@PathVariable userId : String): String {
        return "get User = $userId"
    }

    @PostMapping("{userId}")
    fun addUser(@PathVariable userId: String): String {
        return "add User = $userId"
    }

    @DeleteMapping("{userId}")
    fun deleteUser(@PathVariable userId: String): String {
        return "delete User = $userId"
    }

    @PatchMapping("{userId}")
    fun updateUser(@PathVariable userId: String): String {
        return "update User = $userId"
    }
}