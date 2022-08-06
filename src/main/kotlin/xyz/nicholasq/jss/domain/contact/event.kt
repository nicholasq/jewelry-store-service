package xyz.nicholasq.jss.domain.contact

import jakarta.inject.Singleton
import xyz.nicholasq.jss.infrastructure.event.AbstractFakeEventPublisherService
import xyz.nicholasq.jss.infrastructure.event.EventType
import xyz.nicholasq.jss.infrastructure.event.ServiceEvent
import xyz.nicholasq.jss.infrastructure.event.ServiceType
import xyz.nicholasq.jss.infrastructure.service.Dto
import java.time.LocalDateTime

data class ContactCreateEvent(
    override val id: String,
    override val body: Contact
) : ServiceEvent<Dto> {

    override val eventType: EventType = EventType.CREATE
    override val eventDate: LocalDateTime = LocalDateTime.now()
    override val serviceType: ServiceType = ServiceType.CONTACT
}

data class ContactUpdateEvent(
    override val id: String,
    override val body: Contact
) : ServiceEvent<Dto> {

    override val eventType: EventType = EventType.UPDATE
    override val eventDate: LocalDateTime = LocalDateTime.now()
    override val serviceType: ServiceType = ServiceType.CONTACT
}

data class ContactDeleteEvent(
    override val id: String,
    override val body: Contact
) : ServiceEvent<Dto> {

    override val eventType: EventType = EventType.DELETE
    override val eventDate: LocalDateTime = LocalDateTime.now()
    override val serviceType: ServiceType = ServiceType.CONTACT
}

@Singleton
class ContactEventPublisherService : AbstractFakeEventPublisherService<String, Contact>()
