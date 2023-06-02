package com.data

import kotlinx.serialization.Serializable

@Serializable

data class MissionTemplate(
    val id: Int,
    val title: String,
    val location: String,
    val description: String,
    val date: String,
    val timeStart: String,
    val timeStop: String,
    val userId: Int
)