package com.effectivenorth.meritmoney.repository

import com.effectivenorth.meritmoney.entity.UserEntity
import org.springframework.data.repository.CrudRepository
import java.util.*

/**
 * Manages storage and retrieval of User objects from persistent store.
 */
interface UserRepository : CrudRepository<UserEntity, UUID> {

//    fun findOne(id: UUID): User? {
//        TODO("not implemented")
//    }
//
//    fun save(user: User): User? {
//        TODO("not implemented")
//    }
//
//    fun findAll(): List<User> {
//        TODO("not properly implemented")
//        val results: List<User> = listOf<User>()
//        return results
//    }
}