package com.n26.fees.infrastructure

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FeesApplication

fun main(args: Array<String>) {
    runApplication<FeesApplication>(*args)
}
