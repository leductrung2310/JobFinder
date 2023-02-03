package com.example.jobfinder.data.model


data class Job(
    val id: String? = null,
    val title: String? = null,
    val location: String? = null,
    val company_profile: String? = null,
    val company_email: String? = null,
    val requirements: String? = null,
    val telecommuting: Boolean? = null,
    val has_company_logo: Boolean? = null,
    val has_questions: Boolean? = null,
    val employee_type: String? = null,
    val required_experience: String? = null,
    val required_education: String? = null,
    val industry: String? = null,
    val function: String? = null,
    val salary_range: String? = null,
    val report_count: List<String>? = null,
    val created_date: String? = null,
    val is_fake: Boolean? = null
)

fun Job.toMap(): Map<String, Any?> {
    return mapOf(
        "title" to this.title,
        "location" to this.location,
        "company_profile" to this.company_profile,
        "company_email" to this.company_email,
        "requirements" to this.requirements,
        "telecommuting" to this.telecommuting,
        "has_company_logo" to this.has_company_logo,
        "has_questions" to this.has_questions,
        "employee_type" to this.employee_type,
        "required_experience" to this.required_experience,
        "required_education" to this.required_education,
        "industry" to this.industry,
        "function" to this.function,
        "salary_range" to this.salary_range,
        "report_count" to this.report_count,
        "created_date" to this.created_date,
        "is_fake" to this.is_fake
    )
}