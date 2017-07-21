package com.effectivenorth.meritmoney.entity

import org.junit.Test
import java.util.*
import kotlin.test.assertEquals

/**
 * Tests to confirm the UserEntity behaves as expected.
 */
class UserEntityTest {

    @Test
    fun canCreateUserEntity() {

        val id = UUID.randomUUID()
        val forename = "Bob"
        val surname = "Jones"

        val testEntity: UserEntity = UserEntity(id, forename, surname)

        assertEquals(id, testEntity.id)
        assertEquals(forename, testEntity.forename)
        assertEquals(surname, testEntity.surname)
    }
}