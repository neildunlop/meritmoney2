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
}