package xyz.nicholasq.jss.domain.contact

import io.micronaut.core.annotation.Introspected
import xyz.nicholasq.jss.infrastructure.data.Entity

@Introspected
data class ContactEntity(
    override var id: String?,
    val firstName: String?,
    val lastName: String?,
    val dateOfBirth: String?,
    val email: String?,
    val phone: String?,
    val address: String?,
    val company: String?,
    val jobTitle: String?,
    val notes: String?
) : Entity
