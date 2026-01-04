package com.chivalry.api.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

data class ApiError(
    val status: Int,
    val error: String,
    val message: String
)

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgument(e: IllegalArgumentException): ResponseEntity<ApiError> =
        ResponseEntity.badRequest().body(
            ApiError(
                status = HttpStatus.BAD_REQUEST.value(),
                error = "Bad Request",
                message = e.message ?: "Invalid argument"
            )
        )

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<ApiError> =
        ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            ApiError(
                status = HttpStatus.NOT_FOUND.value(),
                error = "Not Found",
                message = e.message ?: "Resource not found"
            )
        )
}
