package com.effectivenorth.meritmoney.controller

import com.effectivenorth.meritmoney.domain.OrgValue
import com.effectivenorth.meritmoney.entity.OrgValueEntity
import com.effectivenorth.meritmoney.exceptions.NotFoundItemException
import com.effectivenorth.meritmoney.repository.OrgValueRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

/**
 * Handles API calls for operation on OrgValues.
 */
@RestController
@RequestMapping("/api/v1/values")
class OrgValueController(val orgValueRepository: OrgValueRepository) {
//Note that constructor injection is the preferred approach in Kotlin.. and I like it too.

    @GetMapping(value = "/{id}", produces = arrayOf("application/json"))
    fun getOrgValue(@PathVariable id: String): OrgValue {
        val foundOrgValue = orgValueRepository.findOne(UUID.fromString(id)) ?: throw NotFoundItemException()
        return OrgValue.createFromEntity(foundOrgValue)
    }

    @GetMapping(produces = arrayOf("application/json"))
    fun getAllOrgValues(): ResponseEntity<List<OrgValue>> =
            ResponseEntity(OrgValue.createFromEntities(orgValueRepository.findAll().toList()), HttpStatus.OK)

    @PostMapping(consumes = arrayOf("application/json"), produces = arrayOf("application/json"))
    fun addOrgValue(@RequestBody orgValue: OrgValue): ResponseEntity<OrgValue> =
            ResponseEntity(OrgValue.createFromEntity(orgValueRepository.save(OrgValueEntity.createFromOrgValue(orgValue))), HttpStatus.CREATED)

    @DeleteMapping(value = "/{id}")
    fun deleteOrgValue(@PathVariable id: String) {
        return  orgValueRepository.delete(UUID.fromString(id))
    }
}