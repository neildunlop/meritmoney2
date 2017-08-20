package com.effectivenorth.meritmoney.domain

import com.effectivenorth.meritmoney.entity.OrgValueEntity
import org.junit.Test
import java.util.*
import kotlin.test.assertEquals

/**
 * Confirms the OrgValue class behaves as expected.
 */
class OrgValueTest {

    @Test
    fun canCreateOrgValue() {

        val id = UUID.randomUUID()
        val title = "Teamwork"
        val description = "Working as a team is better than working alone."

        val testValue = OrgValue(id, title, description)

        assertEquals(id, testValue.id)
        assertEquals(title, testValue.title)
        assertEquals(description, testValue.description)
    }

    @Test
    fun canCreateOrgValueFromOrgValueEntity() {

        val id = UUID.randomUUID()
        val title = "Technical Excellence"
        val description = "Technical excellence is the foundation of all we do."
        val testOrgValueEntity = OrgValueEntity(id, title, description)

        val testOrgValue = OrgValue.createFromEntity(testOrgValueEntity)

        assertEquals(id, testOrgValue.id)
        assertEquals(title, testOrgValue.title)
        assertEquals(description, testOrgValue.description)
    }

    @Test
    fun canCreateOrgValueCollectionFromEntities() {

        val id1 = UUID.randomUUID()
        val title1 = "Smart"
        val description1 = "Working smarter, not harder is always better."

        val id2 = UUID.randomUUID()
        val title2 = "Technical Excellence"
        val description2 = "Knowing how to use our tools is essential."

        val testOrgValue1 = OrgValueEntity(id1, title1, description1)
        val testOrgValue2 = OrgValueEntity(id2, title2, description2)

        val orgValueEntities = listOf(testOrgValue1, testOrgValue2)

        val orgValues = OrgValue.createFromEntities(orgValueEntities)

        assertEquals(2, orgValueEntities.size)
        assertEquals(id1, orgValues[0].id)
        assertEquals(title1, orgValues[0].title)
        assertEquals(description1, orgValues[0].description)
        assertEquals(id2, orgValues[1].id)
        assertEquals(title2, orgValues[1].title)
        assertEquals(description2, orgValues[1].description)
    }
}