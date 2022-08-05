package xyz.nicholasq.jss.domain.contact

import io.micronaut.core.annotation.Introspected
import xyz.nicholasq.jss.infrastructure.entity.Entity

@Introspected
data class ContactEntity<K>(
    override var id: K?,
    val firstName: String?,
    val lastName: String?,
    val dateOfBirth: String?,
    val email: String?,
    val phone: String?,
    val address: String?,
    val company: String?,
    val jobTitle: String?,
    val notes: String?
) : Entity<K>
