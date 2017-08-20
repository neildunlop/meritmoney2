package com.effectivenorth.meritmoney.entity

import com.effectivenorth.meritmoney.domain.User
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

        val testEntity = UserEntity(id, forename, surname)

        assertEquals(id, testEntity.id)
        assertEquals(forename, testEntity.forename)
        assertEquals(surname, testEntity.surname)
    }

    @Test
    fun canCreateFromUser() {

        val id = UUID.randomUUID()
        val forename = "Bob"
        val surname = "Jones"

        val testUser = User(id, forename, surname)

        val userEntity = UserEntity.createFromUser(testUser)

        assertEquals(id, userEntity.id)
        assertEquals(forename, userEntity.forename)
        assertEquals(surname, userEntity.surname)
    }

    @Test
    fun canCreateFromUserCollection() {

        val id1 = UUID.randomUUID()
        val forename1 = "Bob"
        val surname1 = "Jones"

        val id2 = UUID.randomUUID()
        val forename2 = "Steve"
        val surname2 = "Smith"


        val testUser1 = User(id1, forename1, surname1)
        val testUser2 = User(id2, forename2, surname2)

        val testUsers = listOf(testUser1, testUser2)

        val userEntities = UserEntity.createFromUsers(testUsers)

        assertEquals(testUsers.size, userEntities.size)
        assertEquals(id1, userEntities[0].id)
        assertEquals(forename1, userEntities[0].forename)
        assertEquals(surname1, userEntities[0].surname)
        assertEquals(id2, userEntities[1].id)
        assertEquals(forename2, userEntities[1].forename)
        assertEquals(surname2, userEntities[1].surname)
    }
}