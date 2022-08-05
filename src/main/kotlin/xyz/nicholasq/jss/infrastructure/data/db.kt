package xyz.nicholasq.jss.infrastructure.data

import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.firestore.Firestore
import com.google.cloud.firestore.FirestoreOptions
import jakarta.inject.Singleton

interface FirestoreDb {
    val db: Firestore
}

@Singleton
class DefaultFirestoreDb(
    gcloudConfig: GCloudConfiguration,
) : FirestoreDb {

    private val firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder()
        .setProjectId(gcloudConfig.projectId)
        .setCredentials(GoogleCredentials.getApplicationDefault()) // todo: maybe have this injected?
        .build()

    override val db = firestoreOptions.service!!
}
