package com.effectivenorth.meritmoney.repository

import com.effectivenorth.meritmoney.entity.OrgValueEntity
import com.effectivenorth.meritmoney.entity.UserEntity
import org.springframework.data.repository.CrudRepository
import java.util.*

/**
 * Manages storage and retrieval of OrgValue objects from persistent store.
 */
interface OrgValueRepository : CrudRepository<OrgValueEntity, UUID> {

    fun findByTitle(title: String): OrgValueEntity
}