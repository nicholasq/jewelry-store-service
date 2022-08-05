package xyz.nicholasq.jss.infrastructure.service

import jakarta.inject.Named
import jakarta.inject.Singleton
import xyz.nicholasq.jss.infrastructure.data.CrudRepository
import xyz.nicholasq.jss.infrastructure.entity.Entity
import xyz.nicholasq.jss.infrastructure.logging.logger
import xyz.nicholasq.jss.infrastructure.transformer.DtoToEntityTransformer
import xyz.nicholasq.jss.infrastructure.transformer.EntityToDtoTransformer

interface CrudService<K, T : Dto<K>> {
    suspend fun save(dto: T): T
    suspend fun update(dto: T): T
    suspend fun findById(id: String): T
    suspend fun findAll(): List<T>
    suspend fun delete(id: String): Boolean
}

@Singleton
@Named("baseCrudService")
class BaseCrudService<K, T1 : Dto<K>, T2 : Entity<K>>(
    private val repository: CrudRepository<K, T2>,
    private val dtoToEntityTransformer: DtoToEntityTransformer<K, T1, T2>,
    private val entityToDtoTransformer: EntityToDtoTransformer<K, T2, T1>
) : CrudService<K, T1> {

    private val log = logger()

    var postSaveProcess = { dto: T1 -> dto }

    var postUpdateProcess = { dto: T1 -> dto }

    var postDeleteProcess = { dto: T1 -> dto }

    override suspend fun save(dto: T1): T1 {
        log.debug("save() - dto: $dto")
        val entity: T2 = dtoToEntityTransformer.transform(dto)
        val savedEntity: T2 = repository.save(entity)
        val savedDto: T1 = entityToDtoTransformer.transform(savedEntity)
        postSaveProcess(savedDto)
        log.debug("save() - savedDto: $savedDto")
        return savedDto
    }

    override suspend fun update(dto: T1): T1 {
        log.debug("update() - dto: $dto")
        val entity: T2 = dtoToEntityTransformer.transform(dto)
        val updatedEntity: T2 = repository.update(entity)
        val updatedDto: T1 = entityToDtoTransformer.transform(updatedEntity)
        postUpdateProcess(updatedDto)
        log.debug("update() - updatedDto: $updatedDto")
        return updatedDto
    }

    override suspend fun findById(id: String): T1 {
        log.debug("findById() - id: $id")
        val entity: T2 = repository.findById(id)
        val dto: T1 = entityToDtoTransformer.transform(entity)
        log.debug("findById() - dto: $dto")
        return dto
    }

    override suspend fun findAll(): List<T1> {
        log.debug("findAll()")
        val entities: List<T2> = repository.findAll()
        val dtos: List<T1> = entities.map { entityToDtoTransformer.transform(it) }
        log.debug("findAll() - dtos: $dtos")
        return dtos
    }

    override suspend fun delete(id: String): Boolean {
        log.debug("delete() - id: $id")
        val dto = findById(id)
        val deleted = repository.delete(id)
        log.debug("delete() - deleted: $deleted dto: $dto")
        postDeleteProcess(dto)
        return deleted
    }
}

