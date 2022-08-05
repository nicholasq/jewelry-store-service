package xyz.nicholasq.jss.domain.contact

import xyz.nicholasq.jss.infrastructure.api.CreateCommand
import xyz.nicholasq.jss.infrastructure.api.UpdateCommand
import java.time.LocalDateTime
import java.time.ZonedDateTime

abstract class ContactCommand(
    open val firstName: String?,
    open val lastName: String?,
    open val dateOfBirth: ZonedDateTime?,
    open val email: String?,
    open val phone: String?,
    open val address: String?,
    open val company: String?,
    open val jobTitle: String?,
    open val notes: String?
)

data class CreateContactCommand(
    override val firstName: String? = null,
    override val lastName: String? = null,
    override val dateOfBirth: ZonedDateTime? = null,
    override val email: String? = null,
    override val phone: String? = null,
    override val address: String? = null,
    override val company: String? = null,
    override val jobTitle: String? = null,
    override val notes: String? = null
) : ContactCommand(firstName, lastName, dateOfBirth, email, phone, address, company, jobTitle, notes), CreateCommand

data class UpdateContactCommand(
    override val firstName: String? = null,
    override val lastName: String? = null,
    override val dateOfBirth: ZonedDateTime? = null,
    override val email: String? = null,
    override val phone: String? = null,
    override val address: String? = null,
    override val company: String? = null,
    override val jobTitle: String? = null,
    override val notes: String? = null
) : ContactCommand(firstName, lastName, dateOfBirth, email, phone, address, company, jobTitle, notes), UpdateCommand
