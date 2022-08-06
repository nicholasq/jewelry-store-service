package xyz.nicholasq.jss.domain.contact

import jakarta.inject.Singleton
import xyz.nicholasq.jss.infrastructure.service.BaseCrudService
import xyz.nicholasq.jss.infrastructure.service.CrudService
import xyz.nicholasq.jss.infrastructure.service.Dto

interface ContactService<T : Dto> : CrudService<T>

@Singleton
class DefaultContactService(
    contactRepository: ContactRepository,
) : ContactService<Contact> {

    // todo: maybe i should inject this service
    private val baseCrudService = BaseCrudService(
        repository = contactRepository,
        dtoToEntityTransformer = DefaultContactDtoToEntityTransformer(),
        entityToDtoTransformer = DefaultContactEntityToDtoTransformer()
    )

    override suspend fun save(dto: Contact): Contact {
        return baseCrudService.save(dto)
    }

    override suspend fun update(dto: Contact): Contact {
        return baseCrudService.update(dto)
    }

    override suspend fun findById(id: String): Contact {
        return baseCrudService.findById(id)
    }

    override suspend fun findAll(): List<Contact> {
        return baseCrudService.findAll()
    }

    override suspend fun delete(id: String): Boolean {
        return baseCrudService.delete(id)
    }
}
