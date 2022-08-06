package xyz.nicholasq.jss.infrastructure.data

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.google.api.core.ApiFuture
import com.google.api.core.ApiFutureCallback
import com.google.api.core.ApiFutures
import com.google.cloud.firestore.DocumentReference
import com.google.cloud.firestore.DocumentSnapshot
import jakarta.inject.Named
import jakarta.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor
import java.io.IOException
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


interface CrudRepository<T : Entity> {
    suspend fun save(entity: T): T
    suspend fun update(entity: T): T
    suspend fun findById(id: String): T
    suspend fun findAll(): List<T>
    suspend fun delete(id: String): Boolean
}

@Singleton
@Named("baseFirestoreRepository")
class BaseFirestoreCrudRepository<T : Entity>(
    firestoreDb: FirestoreDb,
    firestoreCollectionConfig: FirestoreCollectionConfiguration
) : CrudRepository<T> {

    private val db = firestoreDb.db
    private val collectionName = firestoreCollectionConfig.collection
    override suspend fun save(entity: T): T {
        TODO("Not yet implemented")
    }

    override suspend fun update(entity: T): T {
        TODO("Not yet implemented")
    }

    override suspend fun findById(id: String): T {
        TODO("Not yet implemented")
    }

    override suspend fun findAll(): List<T> {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: String): Boolean {
        TODO("Not yet implemented")
    }

    //    override suspend fun save(entity: Entity<String>): Entity<String> {
//        val id = db.collection(collectionName).document().id
//        entity.id = id
//        val item: ApiFuture<DocumentReference> = db.collection(collectionName).add(entity)
//        return item.await(entity::class.java)
//    }
//
//    override suspend fun update(entity: Entity<String>): Entity<String> {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun findById(id: String): Entity<String> {
//        val docRef = db.collection(collectionName).document(id)
//        return docRef.get().await { it.toObject() }
//    }
//
//    override suspend fun findAll(): List<Entity<String>> {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun delete(id: String): Boolean {
//        TODO("Not yet implemented")
//    }

}

private val objectMapper = jacksonObjectMapper()

private suspend fun <T> ApiFuture<DocumentReference>.await(clazz: Class<T>): T {
    val docRef = this.await { it }
    val docSnap = docRef.get().await { it.data }
    return objectMapper.convertValue(docSnap, clazz)
}

private inline fun <reified T : Any> DocumentSnapshot.toObject(): T {
    return objectMapper.convertValue(this, T::class.java)
}

private suspend fun <F : Any?, R : Any?> ApiFuture<F>.await(
    successHandler: (F) -> R,
): R {
    return suspendCoroutine { cont: Continuation<R> ->
        ApiFutures.addCallback(this, object : ApiFutureCallback<F> {
            override fun onFailure(t: Throwable?) {
                cont.resumeWithException(t ?: IOException("Unknown error"))
            }

            override fun onSuccess(result: F) {
                cont.resume(successHandler(result))
            }
        }, Dispatchers.IO.asExecutor())
    }
}
