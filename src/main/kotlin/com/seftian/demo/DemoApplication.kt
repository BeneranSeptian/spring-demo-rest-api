package com.seftian.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}

//@RestController
//class MessageController {
//    @GetMapping("/")
//    fun index(@RequestParam("name") name: String) = "Hello, $name!"
//}