package com.example.jobfinder.data.model


data class Job(
    val id: String? = null,
    val name: String? = null,
    val company: String? = null,
)

fun Job.toMap(): Map<String, Any?> {
    return mapOf("name" to this.name, "company" to this.company)
}