package xyz.nicholasq.jss.domain.contact

import io.micronaut.context.annotation.Primary
import jakarta.inject.Singleton
import xyz.nicholasq.jss.infrastructure.data.BaseFirestoreCrudRepository
import xyz.nicholasq.jss.infrastructure.data.CrudRepository
import xyz.nicholasq.jss.infrastructure.data.FirestoreCollectionConfiguration
import xyz.nicholasq.jss.infrastructure.data.FirestoreDb

interface ContactRepository : CrudRepository<String, ContactEntity<String>>

@Singleton
@Primary
class DefaultContactFirestoreRepository(
    firestoreDb: FirestoreDb,
    firestoreCollectionConfig: FirestoreCollectionConfiguration
) : ContactRepository {

    val baseFirestoreCrudRepository = BaseFirestoreCrudRepository(firestoreDb, firestoreCollectionConfig)

    override suspend fun save(entity: ContactEntity<String>): ContactEntity<String> {
        return baseFirestoreCrudRepository.save(entity)
    }

    override suspend fun update(entity: ContactEntity<String>): ContactEntity<String> {
        TODO("Not yet implemented")
    }

    override suspend fun findById(id: String): ContactEntity<String> {
        TODO("Not yet implemented")
    }

    override suspend fun findAll(): List<ContactEntity<String>> {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: String): Boolean {
        TODO("Not yet implemented")
    }
}
