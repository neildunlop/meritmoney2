package com.effectivenorth.meritmoney.domain

import com.effectivenorth.meritmoney.entity.OrgValueEntity
import mu.KotlinLogging
import java.util.*

class OrgValue(val id: UUID, val title:String, val description:String) {

    private val logger = KotlinLogging.logger {}

    init {
        logger.debug { "Org Value $id initialised." }
    }

    companion object {
        fun createFromEntity(entity: OrgValueEntity) : OrgValue {
            return OrgValue(entity.id, entity.title, entity.description)
        }

        fun createFromEntities(entities: List<OrgValueEntity>) : List<OrgValue> {
            return entities.map { it-> OrgValue(it.id, it.title, it.description) }
        }
    }
}