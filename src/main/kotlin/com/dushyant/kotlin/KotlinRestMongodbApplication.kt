package com.dushyant.kotlin

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class KotlinRestMongodbApplication

fun main(args: Array<String>) {
    SpringApplication.run(KotlinRestMongodbApplication::class.java, *args)
}
