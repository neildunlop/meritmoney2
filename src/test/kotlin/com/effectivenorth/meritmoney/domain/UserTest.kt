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
        assertEquals(0.0, testUser.balance)
        assertEquals(0.0, testUser.credit)
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
        assertEquals(0.0, testUser.balance)
        assertEquals(0.0, testUser.credit)
    }

    @Test
    fun canCreateUserCollectionFromEntities() {

        val id1 = UUID.randomUUID()
        val forename1 = "Bob"
        val surname1 = "Jones"

        val id2 = UUID.randomUUID()
        val forename2 = "Steve"
        val surname2 = "Smith"

        val testUser1 = User(id1, forename1, surname1)
        val testUser2 = User(id2, forename2, surname2)

        val userEntities = listOf(testUser1, testUser2)

        val users = UserEntity.createFromUsers(userEntities)

        assertEquals(2, userEntities.size)
        assertEquals(id1, users[0].id)
        assertEquals(forename1, users[0].forename)
        assertEquals(surname1, users[0].surname)
        assertEquals(id2, users[1].id)
        assertEquals(forename2, users[1].forename)
        assertEquals(surname2, users[1].surname)
    }
}