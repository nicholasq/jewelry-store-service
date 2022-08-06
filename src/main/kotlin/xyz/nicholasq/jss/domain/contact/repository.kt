package xyz.nicholasq.jss.domain.contact

import io.micronaut.context.annotation.Primary
import jakarta.inject.Singleton
import xyz.nicholasq.jss.infrastructure.data.*

interface ContactRepository : CrudRepository<ContactEntity>

@Singleton
@Primary
class DefaultContactFirestoreRepository(
    firestoreDb: FirestoreDb,
    firestoreCollectionConfig: FirestoreCollectionConfiguration
) : ContactRepository {

    private val baseFirestoreCrudRepository =
        BaseFirestoreCrudRepository<Entity>(firestoreDb, firestoreCollectionConfig)

    override suspend fun save(entity: ContactEntity): ContactEntity {
        TODO("Not yet implemented")
    }

    override suspend fun update(entity: ContactEntity): ContactEntity {
        TODO("Not yet implemented")
    }

    override suspend fun findById(id: String): ContactEntity {
        TODO("Not yet implemented")
    }

    override suspend fun findAll(): List<ContactEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: String): Boolean {
        TODO("Not yet implemented")
    }
}
