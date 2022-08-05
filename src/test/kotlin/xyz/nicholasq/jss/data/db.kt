package xyz.nicholasq.jss.data

import com.google.cloud.NoCredentials
import com.google.cloud.firestore.FirestoreOptions
import io.micronaut.context.annotation.Primary
import jakarta.inject.Singleton
import xyz.nicholasq.jss.infrastructure.data.FirestoreDb
import xyz.nicholasq.jss.infrastructure.data.GCloudConfiguration


@Singleton
@Primary
class FirestoreTestDb(
    gcloudConfig: GCloudConfiguration,
    firestoreEmulator: FirestoreEmulator
) : FirestoreDb {

    private val firestoreOptions by lazy {
        FirestoreOptions
            .getDefaultInstance()
            .toBuilder()
            .setHost(firestoreEmulator.emulatorEndpoint)
            .setCredentials(NoCredentials.getInstance())
            .setProjectId(gcloudConfig.projectId)
            .build()
    }

    override val db = firestoreOptions.service!!
}
