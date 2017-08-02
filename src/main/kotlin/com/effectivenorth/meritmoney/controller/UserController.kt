package com.effectivenorth.meritmoney.controller

import com.effectivenorth.meritmoney.domain.User
import com.effectivenorth.meritmoney.entity.UserEntity
import com.effectivenorth.meritmoney.repository.UserRepository
import org.springframework.web.bind.annotation.*
import java.util.*

/**
 * Handles API calls for operation on Users.
 */
@RestController
@RequestMapping("/api/v1/users")
class UserController(val userRepository: UserRepository) {
//Note that constructor injection is the preferred approach in Kotlin.. and I like it too.

//    @RequestMapping(method = arrayOf(RequestMethod.GET), produces = arrayOf("application/json"))
//    fun getUser(@PathVariable id: UUID): User? = User.createFromEntity(userRepository.findOne(id))

    @RequestMapping(method = arrayOf(RequestMethod.GET), produces = arrayOf("application/json"))
    fun getAllUsers(): List<User> = User.createFromEntities(userRepository.findAll().toList())

    @RequestMapping(method = arrayOf(RequestMethod.GET),
            consumes = arrayOf("application/json"),
            produces = arrayOf("application/json"))
    fun addUser(@RequestBody user: User): User = User.createFromEntity(userRepository.save(UserEntity.createFromUser(user)))

    //TODO("Delete method")
}