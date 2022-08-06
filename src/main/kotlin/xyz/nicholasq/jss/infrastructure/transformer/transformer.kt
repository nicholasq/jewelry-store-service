package xyz.nicholasq.jss.infrastructure.transformer

import xyz.nicholasq.jss.domain.contact.Contact
import xyz.nicholasq.jss.infrastructure.api.CreateCommand
import xyz.nicholasq.jss.infrastructure.api.UpdateCommand
import xyz.nicholasq.jss.infrastructure.data.Entity
import xyz.nicholasq.jss.infrastructure.event.ServiceEvent
import xyz.nicholasq.jss.infrastructure.service.Dto

interface Transformer<in E, out T> {
    fun transform(obj: E): T
}

interface EntityToDtoTransformer<T1 : Entity, T2 : Dto> : Transformer<T1, T2>

interface DtoToEntityTransformer<T1 : Dto, T2 : Entity> : Transformer<T1, T2>

interface DtoToEventTransformer<T1 : Dto, T2 : ServiceEvent<Dto>> : Transformer<T1, T2>

interface CreateCommandToDtoTransformer<T1 : CreateCommand, T2 : Dto> : Transformer<T1, T2>

interface UpdateCommandToDtoTransformer<T1 : UpdateCommand, T2 : Dto> : Transformer<T1, T2>

interface DtoToCreateEventTransformer<K, T1 : Dto, T2 : ServiceEvent<Dto>> :
    DtoToEventTransformer<T1, T2>

interface DtoToUpdateEventTransformer<K, T1 : Dto, T2 : ServiceEvent<Dto>> :
    DtoToEventTransformer<T1, T2>

interface DtoToDeleteEventTransformer<K, T1 : Dto, T2 : ServiceEvent<Dto>> :
    DtoToEventTransformer<T1, T2>

interface DtoToEventTransformers {
    fun transformCreate(dto: Contact): ServiceEvent<Dto>
    fun transformUpdate(dto: Contact): ServiceEvent<Dto>
    fun transformDelete(dto: Contact): ServiceEvent<Dto>
}

