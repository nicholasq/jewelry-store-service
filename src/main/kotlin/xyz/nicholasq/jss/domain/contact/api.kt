package xyz.nicholasq.jss.domain.contact

import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.*
import xyz.nicholasq.jss.infrastructure.api.BaseCrudController
import xyz.nicholasq.jss.infrastructure.service.Dto
import xyz.nicholasq.jss.infrastructure.transformer.CreateCommandToDtoTransformer
import xyz.nicholasq.jss.infrastructure.transformer.UpdateCommandToDtoTransformer

@Controller("/api/v1/contacts")
class ContactController(
    contactService: ContactService<String, Contact<String>>,
    createCommandTransformer: CreateCommandToDtoTransformer<String, CreateContactCommand, Contact<String>>,
    updateCommandTransformer: UpdateCommandToDtoTransformer<String, UpdateContactCommand, Contact<String>>
) {

    // maybe we should inject this
    private val baseCrudController = BaseCrudController(contactService, createCommandTransformer, updateCommandTransformer)

    @Post("/")
    @Status(HttpStatus.CREATED)
    suspend fun create(@Body createCommand: CreateContactCommand): Contact<String> {
        return baseCrudController.create(createCommand)
    }

    @Get("/{id}")
    @Status(HttpStatus.OK)
    suspend fun find(@PathVariable id: String): Contact<String> {
        return baseCrudController.find(id)
    }

    @Put("/{id}")
    @Status(HttpStatus.OK)
    suspend fun update(@PathVariable id: String, @Body updateCommand: UpdateContactCommand): Contact<String> {
        return baseCrudController.update(id, updateCommand)
    }

    @Delete("/{id}")
    @Status(HttpStatus.NO_CONTENT)
    suspend fun delete(@PathVariable id: String): HttpStatus {
        return baseCrudController.delete(id)
    }

    @Get("/")
    @Status(HttpStatus.OK)
    suspend fun findAll(): List<Dto<String>> {
        return baseCrudController.findAll()
    }
}
