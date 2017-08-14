package com.effectivenorth.meritmoney.controller

import com.effectivenorth.meritmoney.domain.User
import com.effectivenorth.meritmoney.entity.UserEntity
import com.effectivenorth.meritmoney.exceptions.NotFoundItemException
import com.effectivenorth.meritmoney.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

/**
 * Handles API calls for operation on Users.
 */
@RestController
@RequestMapping("/api/v1/users")
class UserController(val userRepository: UserRepository) {
//Note that constructor injection is the preferred approach in Kotlin.. and I like it too.

    @GetMapping(value = "/{id}", produces = arrayOf("application/json"))
    fun getUser(@PathVariable id: String): User {

        val foundUser = userRepository.findOne(UUID.fromString(id)) ?: throw NotFoundItemException()
        return User.createFromEntity(foundUser)
    }


    @GetMapping(produces = arrayOf("application/json"))
    fun getAllUsers(): List<User> = User.createFromEntities(userRepository.findAll().toList())


    @PostMapping(consumes = arrayOf("application/json"),
            produces = arrayOf("application/json"))
    fun addUser(@RequestBody user: User): ResponseEntity<User> =
            ResponseEntity(User.createFromEntity(userRepository.save(UserEntity.createFromUser(user))), HttpStatus.CREATED)

    //TODO("Delete method")
}