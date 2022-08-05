package xyz.nicholasq.jss.infrastructure.event

import org.slf4j.LoggerFactory
import xyz.nicholasq.jss.infrastructure.service.Dto
import java.time.LocalDateTime

interface EventPublisherService<K, T : Dto<K>> {
    fun publishCreate(event: ServiceEvent<T>)
    fun publishUpdate(event: ServiceEvent<T>)
    fun publishDelete(event: ServiceEvent<T>)
}

interface ServiceEvent<T> {
    val id: String
    val eventType: EventType
    val eventDate: LocalDateTime
    val serviceType: ServiceType
    val body: T
}

enum class EventType {
    CREATE, UPDATE, DELETE
}

enum class ServiceType {
    PIPELINE, PHASE, JOB, CONTACT
}

// This class is temporary until I get around to implementing a pubsub version.
abstract class AbstractFakeEventPublisherService<K, T : Dto<K>> : EventPublisherService<K, T> {

    private val log = LoggerFactory.getLogger(this.javaClass)

    override fun publishCreate(event: ServiceEvent<T>) {
        log.debug("publishCreate: $event")
    }

    override fun publishUpdate(event: ServiceEvent<T>) {
        log.debug("publishUpdate: $event")
    }

    override fun publishDelete(event: ServiceEvent<T>) {
        log.debug("publishDelete: $event")
    }
}
