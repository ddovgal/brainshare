package ua.ddovgal.brainshareApi

import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import javax.sql.DataSource

@SpringBootApplication
class BrainshareApiApplication {
    @Bean
    fun dslContext(@Suppress("SpringKotlinAutowiring") dataSource: DataSource): DSLContext
            = DSL.using(dataSource, SQLDialect.MYSQL)
}

fun main(args: Array<String>) {
    SpringApplication.run(BrainshareApiApplication::class.java, *args)
}