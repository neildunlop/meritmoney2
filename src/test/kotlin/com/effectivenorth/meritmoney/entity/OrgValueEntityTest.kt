package com.effectivenorth.meritmoney.entity

import org.junit.Test
import java.util.*
import kotlin.test.assertEquals

/**
 * Tests to confirm the OrgValue behaves as expected.
 */
class OrgValueEntityTest {

    @Test
    fun canCreateOrgValueEntity() {

        val id = UUID.randomUUID()
        val title = "Teamwork"
        val description = "Working as a team is better than working alone."


        val testEntity = OrgValueEntity(id, title, description)

        assertEquals(id, testEntity.id)
        assertEquals(title, testEntity.title)
        assertEquals(description, testEntity.description)
    }


}