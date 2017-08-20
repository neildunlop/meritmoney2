package com.effectivenorth.meritmoney.domain

import com.effectivenorth.meritmoney.entity.KudoEntity
import mu.KotlinLogging
import java.util.*

class Kudo(val id: UUID, val sender:User, val recipient:User, val summary:String, val detail:String,
           val orgValue:OrgValue, val worth: Double) {

    private val logger = KotlinLogging.logger {}

    init {
        logger.debug { "Kudo $id is initialised." }
    }

    companion object {
        fun createFromEntity(entity: KudoEntity) : Kudo {
            return Kudo(entity.id, User.createFromEntity(entity.sender), User.createFromEntity(entity.recipient),
                    entity.summary, entity.detail, OrgValue.createFromEntity(entity.orgValue), entity.worth)
        }

        fun createFromEntities(entities: List<KudoEntity>) : List<Kudo> {
            return entities.map { it-> Kudo(it.id, User.createFromEntity(it.sender), User.createFromEntity(it.recipient),
                    it.summary, it.detail, OrgValue.createFromEntity(it.orgValue), it.worth) }
        }
    }
}