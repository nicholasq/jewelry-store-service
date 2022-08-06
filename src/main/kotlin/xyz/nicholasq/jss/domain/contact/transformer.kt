package xyz.nicholasq.jss.domain.contact

import jakarta.inject.Singleton
import xyz.nicholasq.jss.infrastructure.transformer.CreateCommandToDtoTransformer
import xyz.nicholasq.jss.infrastructure.transformer.DtoToEntityTransformer
import xyz.nicholasq.jss.infrastructure.transformer.EntityToDtoTransformer
import xyz.nicholasq.jss.infrastructure.transformer.UpdateCommandToDtoTransformer
import java.time.ZonedDateTime

interface ContactDtoToEntityTransformer : DtoToEntityTransformer<Contact, ContactEntity>
interface ContactEntityToDtoTransformer : EntityToDtoTransformer<ContactEntity, Contact>

interface ContactCreateCommandToDtoTransformer : CreateCommandToDtoTransformer<CreateContactCommand, Contact>
interface ContactUpdateCommandToDtoTransformer : UpdateCommandToDtoTransformer<UpdateContactCommand, Contact>

@Singleton
class DefaultContactDtoToEntityTransformer : ContactDtoToEntityTransformer {
    override fun transform(obj: Contact): ContactEntity {
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
class DefaultContactEntityToDtoTransformer : ContactEntityToDtoTransformer {

    override fun transform(obj: ContactEntity): Contact {
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
class DefaultContactCreateCommandToDtoTransformer : ContactCreateCommandToDtoTransformer {
    override fun transform(obj: CreateContactCommand): Contact {
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
class DefaultContactUpdateCommandToDtoTransformer : ContactUpdateCommandToDtoTransformer {
    override fun transform(obj: UpdateContactCommand): Contact {
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
