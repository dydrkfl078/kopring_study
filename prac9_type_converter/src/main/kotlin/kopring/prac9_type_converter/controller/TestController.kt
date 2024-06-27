package kopring.prac9_type_converter.controller

import kopring.prac9_type_converter.type.IpPort
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/prac")
class TestController {

    @GetMapping("/1")
    fun stringToInt(@RequestParam source : Int ): Int {
        return source
    }

    @GetMapping("/2")
    fun intToString(@RequestParam source : String ): String {
        return source
    }

    @GetMapping("/ip")
    fun stringToIpPort(@RequestParam source : IpPort ): IpPort {
        return source
    }

    @GetMapping("/ip2")
    fun ipPortToString(@RequestParam source : String ): String {
        return source
    }
}