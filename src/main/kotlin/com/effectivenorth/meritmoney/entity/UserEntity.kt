package com.effectivenorth.meritmoney.entity

import com.effectivenorth.meritmoney.domain.User
import mu.KotlinLogging
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class UserEntity(@Id val id: UUID, val forename: String, val surname: String) {

    private val logger = KotlinLogging.logger {}

    var credit: Double = 0.0
    var balance: Double = 0.0

    init {
        logger.info {"User Entity $id initialised."}
    }

    companion object {
        fun createFromUser(user:User) : UserEntity {
            return UserEntity(user.id, user.forename, user.surname)
        }
    }



}