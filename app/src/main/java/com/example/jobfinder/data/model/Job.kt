package com.example.jobfinder.data.model


data class Job(
    val id: String? = null,
    val name: String? = null,
    val description: String? = null,
    val company: String? = null,
    val salary: String? = null,
    val location: String? = null,
    val createDate: String? = null,
    val isLock: Boolean? = null
)

fun Job.toMap(): Map<String, Any?> {
    return mapOf(
        "name" to this.name,
        "description" to this.description,
        "company" to this.company,
        "salary" to this.salary,
        "location" to this.location,
        "createDate" to this.createDate,
        "isLock" to this.isLock
    )
}