package xyz.nicholasq.jss.domain.contact

import jakarta.inject.Singleton
import xyz.nicholasq.jss.infrastructure.transformer.CreateCommandToDtoTransformer
import xyz.nicholasq.jss.infrastructure.transformer.DtoToEntityTransformer
import xyz.nicholasq.jss.infrastructure.transformer.EntityToDtoTransformer
import xyz.nicholasq.jss.infrastructure.transformer.UpdateCommandToDtoTransformer
import java.time.ZonedDateTime

interface ContactDtoToEntityTransformer<K> : DtoToEntityTransformer<K, Contact<K>, ContactEntity<K>>
interface ContactEntityToDtoTransformer<K> : EntityToDtoTransformer<K, ContactEntity<K>, Contact<K>>

interface ContactCreateCommandToDtoTransformer<K> : CreateCommandToDtoTransformer<K, CreateContactCommand, Contact<K>>
interface ContactUpdateCommandToDtoTransformer<K> : UpdateCommandToDtoTransformer<K, UpdateContactCommand, Contact<K>>

@Singleton
class DefaultContactDtoToEntityTransformer : ContactDtoToEntityTransformer<String> {
    override fun transform(obj: Contact<String>): ContactEntity<String> {
        return ContactEntity(
            id = null,
            firstName = obj.firstName,
            lastName = obj.lastName,
            email = obj.email,
            phone = obj.phone,
            dateOfBirth = obj.dateOfBirth.toString(),
            address = obj.address,
            company = obj.company,
            jobTitle = obj.jobTitle,
            notes = obj.notes,
        )
    }
}

@Singleton
class DefaultContactEntityToDtoTransformer : ContactEntityToDtoTransformer<String> {

    override fun transform(obj: ContactEntity<String>): Contact<String> {
        return Contact(
            id = obj.id,
            firstName = obj.firstName,
            lastName = obj.lastName,
            dateOfBirth = ZonedDateTime.parse(obj.dateOfBirth.toString()),
            email = obj.email,
            phone = obj.phone,
            address = obj.address,
            company = obj.company,
            jobTitle = obj.jobTitle,
            notes = obj.notes
        )
    }
}

@Singleton
class DefaultContactCreateCommandToDtoTransformer : ContactCreateCommandToDtoTransformer<String> {
    override fun transform(obj: CreateContactCommand): Contact<String> {
        return Contact(
            id = null,
            firstName = obj.firstName,
            lastName = obj.lastName,
            dateOfBirth = obj.dateOfBirth,
            email = obj.email,
            phone = obj.phone,
            address = obj.address,
            company = obj.company,
            jobTitle = obj.jobTitle,
            notes = obj.notes
        )
    }
}

@Singleton
class DefaultContactUpdateCommandToDtoTransformer : ContactUpdateCommandToDtoTransformer<String> {
    override fun transform(obj: UpdateContactCommand): Contact<String> {
        return Contact(
            id = null,
            firstName = obj.firstName,
            lastName = obj.lastName,
            dateOfBirth = obj.dateOfBirth,
            email = obj.email,
            phone = obj.phone,
            address = obj.address,
            company = obj.company,
            jobTitle = obj.jobTitle,
            notes = obj.notes
        )
    }
}
