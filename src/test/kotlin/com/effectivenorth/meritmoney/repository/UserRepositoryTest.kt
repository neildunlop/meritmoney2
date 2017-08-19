package com.effectivenorth.meritmoney.repository

import com.effectivenorth.meritmoney.entity.UserEntity
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNull


@RunWith(SpringRunner::class)
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    lateinit var entityManager: TestEntityManager

    @Autowired
    lateinit var userRepository: UserRepository

    @Test
    fun canFindById() {

        val user1 = UserEntity(UUID.randomUUID(), "Bob", "Jones")
        val user2 = UserEntity(UUID.randomUUID(), "Fred", "Smith")

        entityManager.persist(user1)
        entityManager.persist(user2)
        entityManager.flush()

        val result = userRepository.findOne(user1.id)
        assertEquals(user1, result);
    }

    @Test
    fun canFindByForename() {

        val user1 = UserEntity(UUID.randomUUID(), "Bob", "Jones")
        val user2 = UserEntity(UUID.randomUUID(), "Fred", "Smith")

        entityManager.persist(user1)
        entityManager.persist(user2)
        entityManager.flush()

        val result = userRepository.findByForename(user2.forename)
        assertEquals(user2, result);
    }

    @Test
    fun canFindBySurname() {

        val user1 = UserEntity(UUID.randomUUID(), "Bob", "Jones")
        val user2 = UserEntity(UUID.randomUUID(), "Fred", "Smith")

        entityManager.persist(user1)
        entityManager.persist(user2)
        entityManager.flush()

        val result = userRepository.findBySurname(user2.surname)
        assertEquals(user2, result);
    }

    @Test
    fun canSaveNewUser() {

        val user1 = UserEntity(UUID.randomUUID(), "Bob", "Jones")

        val result = userRepository.save(user1)
        assertEquals(user1, result);
    }

    @Test
    fun savingModifiedUserUpdatesExistingEntry() {

        val user1 = UserEntity(UUID.randomUUID(), "Bob", "Jones")
        val modifiedUser1 = user1.copy(forename = "Robert")

        entityManager.persist(user1)
        entityManager.flush()

        //very weird.. the save actually updates the 'user1' instance in memory too - hibernate voodoo
        val result = userRepository.save(modifiedUser1)

        //would expect this to fail.. but it doesn't!
        //assertEquals(user1, modifiedUser1)
        assertEquals(modifiedUser1, result)
    }

    @Test
    fun canDeleteExistingUser() {

        val user1 = UserEntity(UUID.randomUUID(), "Bob", "Jones")

        entityManager.persist(user1)
        entityManager.flush()

        userRepository.delete(user1)

        assertNull(entityManager.find(UserEntity::class.java, user1.id))
    }
}