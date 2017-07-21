package com.effectivenorth.meritmoney

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 * Host for the MeritMoney application.
 */
@SpringBootApplication
class Application {
    //do application initialisation here
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}