package com.effectivenorth.meritmoney.entity

import com.effectivenorth.meritmoney.domain.Kudo
import com.effectivenorth.meritmoney.domain.OrgValue
import com.effectivenorth.meritmoney.domain.User
import mu.KotlinLogging
import java.io.Serializable
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
data class KudoEntity(@Id val id: UUID,
                      @ManyToOne
                      @JoinColumn(name = "senderId")
                      val sender: UserEntity,

                      @ManyToOne
                      @JoinColumn(name = "recipientId")
                      val recipient: UserEntity, val summary:String, val detail:String,


                      @ManyToOne
                      @JoinColumn(name = "orgValueId")
                      val orgValue: OrgValueEntity,

                      val worth: Double) : Serializable {

//    //no-args constructor just for spring data/hibernate's benefit
//    @Suppress("unused")
//    private constructor() : this(id=UUID.randomUUID(), sender = User(), recipient = User(), summary = "", detail = "", orgValue = OrgValue(), worth=0.0)

    @Transient  //make sure JPA doesn't try to serialise this
    private val logger = KotlinLogging.logger {}

    init {
        logger.info {"Kudo Entity $id initialised."}
    }

    companion object {
        fun createFromKudo(kudo: Kudo) : KudoEntity {
            return KudoEntity(kudo.id, UserEntity.createFromUser(kudo.sender), UserEntity.createFromUser(kudo.recipient),
                    kudo.summary, kudo.detail, OrgValueEntity.createFromOrgValue(kudo.orgValue), kudo.worth)
        }

        fun createFromKudos(users: List<Kudo>): List<KudoEntity> {
            return users.map { it -> KudoEntity(it.id, UserEntity.createFromUser(it.sender),
                    UserEntity.createFromUser(it.recipient), it.summary, it.detail,
                    OrgValueEntity.createFromOrgValue(it.orgValue), it.worth)}
        }
    }
}