package xyz.nicholasq.jss.infrastructure.api

import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.PathVariable
import jakarta.inject.Named
import jakarta.inject.Singleton
import xyz.nicholasq.jss.infrastructure.logging.logger
import xyz.nicholasq.jss.infrastructure.service.CrudService
import xyz.nicholasq.jss.infrastructure.service.Dto
import xyz.nicholasq.jss.infrastructure.transformer.CreateCommandToDtoTransformer
import xyz.nicholasq.jss.infrastructure.transformer.UpdateCommandToDtoTransformer

interface CrudController<T1 : CreateCommand, T2 : UpdateCommand, T3 : Dto> {

    suspend fun create(@Body createCommand: T1): T3

    suspend fun find(@PathVariable id: String): T3

    suspend fun update(@PathVariable id: String, @Body updateCommand: T2): T3

    suspend fun delete(@PathVariable id: String): HttpStatus

    suspend fun findAll(): List<T3>
}

@Singleton
@Named("baseCrudController")
class BaseCrudController<T1 : CreateCommand, T2 : UpdateCommand, T3 : Dto>(
    private val crudService: CrudService<T3>,
    private val createCommandTransformer: CreateCommandToDtoTransformer<T1, T3>,
    private val updateCommandTransformer: UpdateCommandToDtoTransformer<T2, T3>
) : CrudController<T1, T2, Dto> {

    private val log = logger()

    override suspend fun create(createCommand: T1): T3 {
        log.debug("create() - createCommand: $createCommand")
        val dto = createCommandTransformer.transform(createCommand)
        val createdDto = crudService.save(dto)
        log.trace("create() - createdDto: $createdDto")
        return createdDto
    }

    override suspend fun find(id: String): T3 {
        log.debug("find() - id: $id")
        val dto = crudService.findById(id)
        log.trace("find() - dto: $dto")
        return dto
    }

    override suspend fun update(id: String, updateCommand: T2): T3 {
        log.debug("update() - id: $id, updateCommand: $updateCommand")
        val dto = updateCommandTransformer.transform(updateCommand)
        val updatedDto = crudService.update(dto)
        log.trace("update() - updatedDto: $updatedDto")
        return updatedDto
    }

    override suspend fun delete(id: String): HttpStatus {
        log.debug("delete() - id: $id")
        val deleted = crudService.delete(id)
        log.trace("delete() - deleted: $deleted")
        return if (deleted) HttpStatus.NO_CONTENT else HttpStatus.NOT_FOUND
    }

    override suspend fun findAll(): List<Dto> {
        log.debug("findAll()")
        val dtoList = crudService.findAll()
        log.trace("findAll() - dtoList: $dtoList")
        return dtoList
    }
}
