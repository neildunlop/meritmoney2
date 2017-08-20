package com.effectivenorth.meritmoney.entity

import com.effectivenorth.meritmoney.domain.User
import mu.KotlinLogging
import java.io.Serializable
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class UserEntity(@Id val id: UUID, val forename: String, val surname: String) : Serializable {

//    //no-args constructor just for spring data/hibernate's benefit
//    @Suppress("unused")
//    private constructor() : this(id = UUID.randomUUID(), forename="", surname="")

    @Transient  //make sure JPA doesn't try to serialise this
    private val logger = KotlinLogging.logger {}

    var credit: Double = 0.0
    var balance: Double = 0.0

    //TODO: Need to add one to many mapping to kudosSent and
    //TODO: Need to add one to many mapping to kudosReceived

    init {
        logger.info {"User Entity $id initialised."}
    }

    companion object {
        fun createFromUser(user:User) : UserEntity {
            return UserEntity(user.id, user.forename, user.surname)
        }

        fun createFromUsers(users: List<User>): List<UserEntity> {
            return users.map { it -> UserEntity(it.id, it.forename, it.surname)}
        }
    }
}