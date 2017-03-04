package ua.ddovgal

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class BrainshareApiApplication

fun main(args: Array<String>) {
    SpringApplication.run(BrainshareApiApplication::class.java, *args)
}
