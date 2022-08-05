package xyz.nicholasq.jss.domain.contact

import jakarta.inject.Singleton
import xyz.nicholasq.jss.infrastructure.service.BaseCrudService
import xyz.nicholasq.jss.infrastructure.service.CrudService
import xyz.nicholasq.jss.infrastructure.service.Dto

interface ContactService<K, T : Dto<K>> : CrudService<K, T>

@Singleton
class DefaultContactService(
    contactRepository: ContactRepository,
) : ContactService<String, Contact<String>> {

    // todo: maybe i should inject this service
    private val baseCrudService = BaseCrudService(
        repository = contactRepository,
        dtoToEntityTransformer = DefaultContactDtoToEntityTransformer(),
        entityToDtoTransformer = DefaultContactEntityToDtoTransformer()
    )

    override suspend fun save(dto: Contact<String>): Contact<String> {
        return baseCrudService.save(dto)
    }

    override suspend fun update(dto: Contact<String>): Contact<String> {
        return baseCrudService.update(dto)
    }

    override suspend fun findById(id: String): Contact<String> {
        return baseCrudService.findById(id)
    }

    override suspend fun findAll(): List<Contact<String>> {
        return baseCrudService.findAll()
    }

    override suspend fun delete(id: String): Boolean {
        return baseCrudService.delete(id)
    }
}
