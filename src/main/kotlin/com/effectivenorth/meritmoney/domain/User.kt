package com.effectivenorth.meritmoney.domain

import com.effectivenorth.meritmoney.entity.UserEntity
import mu.KotlinLogging
import java.util.*

/**
 * A user of the system.
 */
class User(val id: UUID, val forename: String, val surname: String) {

    private val logger = KotlinLogging.logger {}

    var credit: Double = 0.0
    var balance: Double = 0.0

    init {
        logger.info { "User $id initialised." }
    }

    //TODO: Add KudosSend and KudosReceived

    companion object {
        fun createFromEntity(entity: UserEntity): User {
            return User(entity.id, entity.forename, entity.surname)
        }

        fun createFromEntities(entities: List<UserEntity>): List<User> {
            return entities.map { it -> User(it.id, it.forename, it.surname)}
        }
    }
}