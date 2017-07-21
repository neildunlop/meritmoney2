package com.effectivenorth.meritmoney.controller

import com.effectivenorth.meritmoney.domain.User
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.web.client.RestTemplate

/**
 * Tests to confirm the UserController behaves as expected.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerIntegrationTest {

    @Autowired
    lateinit var testRestTemplate : RestTemplate



    @Test
    fun `GET with no parameters returns all elements`() {


        testRestTemplate.getForEntity("/api/v1/users", User::class)
    }
}