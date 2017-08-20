package com.effectivenorth.meritmoney.entity

import com.effectivenorth.meritmoney.domain.OrgValue
import mu.KotlinLogging
import java.io.Serializable
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class OrgValueEntity(@Id val id:UUID, val title: String, val description: String) : Serializable {

    @Suppress("unused")
    private constructor() : this(id=UUID.randomUUID(), title = "", description = "")

    @Transient
    private val logger = KotlinLogging.logger {}

    init {
        logger.info { "OrgValue $id has been initialised." }
    }

    companion object {
        fun createFromOrgValue(orgValue: OrgValue): OrgValueEntity {
            return OrgValueEntity(orgValue.id, orgValue.title, orgValue.description)
        }

        fun createFromOrgValues(orgValues: List<OrgValue>): List<OrgValueEntity> {
            return orgValues.map { it -> OrgValueEntity(it.id, it.title, it.description) }
        }
    }
}