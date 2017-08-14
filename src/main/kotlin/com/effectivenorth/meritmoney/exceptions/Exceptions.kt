package com.effectivenorth.meritmoney.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Item not found.")
class NotFoundItemException: RuntimeException()

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Item already exists.")
class DuplicateItemException: RuntimeException()