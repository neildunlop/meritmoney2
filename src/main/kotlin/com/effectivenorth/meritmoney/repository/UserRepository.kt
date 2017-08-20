package com.effectivenorth.meritmoney.repository

import com.effectivenorth.meritmoney.entity.UserEntity
import org.springframework.data.repository.CrudRepository
import java.util.*

/**
 * Manages storage and retrieval of User objects from persistent store.
 */
interface UserRepository : CrudRepository<UserEntity, UUID> {

    fun findByForename(forename: String): UserEntity
    fun findBySurname(surname: String): UserEntity
}