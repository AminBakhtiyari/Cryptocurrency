package me.bakhtiyari.cryptocurrency.domain.model


data class ErrorModel(
    val throwable: Throwable,
    val type: ErrorEntity,
    val originalMessage: String?
)
