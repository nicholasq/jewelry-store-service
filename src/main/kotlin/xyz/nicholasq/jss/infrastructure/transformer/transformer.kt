package xyz.nicholasq.jss.infrastructure.transformer

import xyz.nicholasq.jss.domain.contact.Contact
import xyz.nicholasq.jss.infrastructure.api.CreateCommand
import xyz.nicholasq.jss.infrastructure.api.UpdateCommand
import xyz.nicholasq.jss.infrastructure.entity.Entity
import xyz.nicholasq.jss.infrastructure.event.ServiceEvent
import xyz.nicholasq.jss.infrastructure.service.Dto

interface Transformer<in E, out T> {
    fun transform(obj: E): T
}

interface EntityToDtoTransformer<K, T1 : Entity<K>, T2 : Dto<K>> : Transformer<T1, T2>

interface DtoToEntityTransformer<K, T1 : Dto<K>, T2 : Entity<K>> : Transformer<T1, T2>

interface DtoToEventTransformer<K, T1 : Dto<K>, T2 : ServiceEvent<Dto<K>>> : Transformer<T1, T2>

interface CreateCommandToDtoTransformer<K, T1 : CreateCommand, T2 : Dto<K>> : Transformer<T1, T2>

interface UpdateCommandToDtoTransformer<K, T1 : UpdateCommand, T2 : Dto<K>> : Transformer<T1, T2>

interface DtoToCreateEventTransformer<K, T1 : Dto<K>, T2 : ServiceEvent<Dto<K>>> :
    DtoToEventTransformer<K, T1, T2>

interface DtoToUpdateEventTransformer<K, T1 : Dto<K>, T2 : ServiceEvent<Dto<K>>> :
    DtoToEventTransformer<K, T1, T2>

interface DtoToDeleteEventTransformer<K, T1 : Dto<K>, T2 : ServiceEvent<Dto<K>>> :
    DtoToEventTransformer<K, T1, T2>

interface DtoToEventTransformers<K> {
    fun transformCreate(dto: Contact<K>): ServiceEvent<Dto<K>>
    fun transformUpdate(dto: Contact<K>): ServiceEvent<Dto<K>>
    fun transformDelete(dto: Contact<K>): ServiceEvent<Dto<K>>
}

