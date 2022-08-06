package xyz.nicholasq.jss.domain.contact

import xyz.nicholasq.jss.infrastructure.service.Dto
import java.time.ZonedDateTime

data class Contact(
    override var id: String?,
    val firstName: String?,
    val lastName: String?,
    val dateOfBirth: ZonedDateTime?,
    val email: String?,
    val phone: String?,
    val address: String?,
    val company: String?,
    val jobTitle: String?,
    val notes: String?
) : Dto
