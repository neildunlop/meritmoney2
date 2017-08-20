package com.effectivenorth.meritmoney.repository

import com.effectivenorth.meritmoney.entity.OrgValueEntity
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
class OrgValueRepositoryTest {

    @Autowired
    lateinit var entityManager: TestEntityManager

    @Autowired
    lateinit var orgValueRepository: OrgValueRepository

    @Test
    fun canFindById() {

        val orgValue1 = OrgValueEntity(UUID.randomUUID(), "Smart", "Knowing what to do is important.")
        val orgValue2 = OrgValueEntity(UUID.randomUUID(), "Technical Excellence", "Knowing how to do it is important.")

        entityManager.persist(orgValue1)
        entityManager.persist(orgValue2)
        entityManager.flush()

        val result = orgValueRepository.findOne(orgValue1.id)
        assertEquals(orgValue1, result);
    }

    @Test
    fun canFindByTitle() {

        val orgValue1 = OrgValueEntity(UUID.randomUUID(), "Smart", "Knowing what to do is important.")
        val orgValue2 = OrgValueEntity(UUID.randomUUID(), "Technical Excellence", "Knowing how to do it is important.")

        entityManager.persist(orgValue1)
        entityManager.persist(orgValue2)
        entityManager.flush()

        val result = orgValueRepository.findByTitle(orgValue2.title)
        assertEquals(orgValue2, result);
    }

    @Test
    fun canSaveNewOrgValue() {

        val orgValue1 = OrgValueEntity(UUID.randomUUID(), "Smart", "Knowing what to do is important.")

        val result = orgValueRepository.save(orgValue1)
        assertEquals(orgValue1, result);
    }

    @Test
    fun savingModifiedOrgValueUpdatesExistingEntry() {

        val orgValue1 = OrgValueEntity(UUID.randomUUID(), "Smart", "Knowing what to do is important.")
        val modifiedOrgValue1 = orgValue1.copy(description = "Know what to do is vital.")

        entityManager.persist(orgValue1)
        entityManager.flush()

        //very weird.. the save actually updates the 'orgValue1' instance in memory too - hibernate voodoo
        val result = orgValueRepository.save(modifiedOrgValue1)

        //would expect this to fail.. but it doesn't!
        //assertEquals(user1, modifiedUser1)
        assertEquals(modifiedOrgValue1, result)
    }

    @Test
    fun canDeleteExistingOrgValue() {

        val orgValue1 = OrgValueEntity(UUID.randomUUID(), "Smart", "Knowing what to do is important.")

        entityManager.persist(orgValue1)
        entityManager.flush()

        orgValueRepository.delete(orgValue1)

        assertNull(entityManager.find(UserEntity::class.java, orgValue1.id))
    }
}