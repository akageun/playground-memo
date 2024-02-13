package kr.geun.oss.memo.route

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeWeb {

    @GetMapping(value = ["", "/"])
    fun main(): String {

        return "home";
    }
}