package com.effectivenorth.meritmoney.domain

import com.effectivenorth.meritmoney.entity.UserEntity
import org.junit.Test
import java.util.*
import kotlin.test.assertEquals

/**
 * Confirms the User class behaves as expected.
 */
class UserTest {

    @Test
    fun canCreateUser() {

        val id: UUID = UUID.randomUUID()
        val forename = "Bob"
        val surname = "Jones"

        val testUser: User = User(id, forename, surname)

        assertEquals(id, testUser.id)
        assertEquals(forename, testUser.forename)
        assertEquals(surname, testUser.surname)
    }

    @Test
    fun canCreateUserFromUserEntity() {

        val id = UUID.randomUUID()
        val forename = "Bob"
        val surname = "Jones"
        val testUserEntity = UserEntity(id, forename, surname)

        val testUser = User.createFromEntity(testUserEntity)

        assertEquals(id, testUser.id)
        assertEquals(forename, testUser.forename)
        assertEquals(surname, testUser.surname)
    }
}