package xyz.nicholasq.jss.data


import jakarta.inject.Singleton
import org.testcontainers.containers.FirestoreEmulatorContainer
import org.testcontainers.utility.DockerImageName

@Singleton
class FirestoreEmulator {

    val image = "gcr.io/google.com/cloudsdktool/cloud-sdk:367.0.0-emulators"

    private var firestoreDBContainer: FirestoreEmulatorContainer? = null

    val emulatorEndpoint: String
        get() {
            if (firestoreDBContainer == null || !firestoreDBContainer!!.isRunning) {
                startFirestoreDb()
            }
            return firestoreDBContainer!!.emulatorEndpoint
        }

    fun startFirestoreDb() {
        if (firestoreDBContainer == null) {
            firestoreDBContainer = FirestoreEmulatorContainer(DockerImageName.parse(image))
                .withExposedPorts(8080)
        }
        if (!firestoreDBContainer!!.isRunning) {
            firestoreDBContainer!!.start()
        }
    }

    fun closeFirestoreDb() {
        if (firestoreDBContainer != null) {
            firestoreDBContainer!!.close()
        }
    }
}
