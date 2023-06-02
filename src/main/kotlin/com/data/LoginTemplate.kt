package com.data

import kotlinx.serialization.Serializable

@Serializable
data class LoginTemplate(
    val username: String,
    val password: String,
)