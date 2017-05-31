package ua.ddovgal.brainshareApi

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableAsync
@EnableTransactionManagement
class BrainshareApiApplication

fun main(args: Array<String>) {
    SpringApplication.run(BrainshareApiApplication::class.java, *args)
}