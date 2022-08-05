package xyz.nicholasq.jss.infrastructure.data

import io.micronaut.context.annotation.ConfigurationProperties

interface FirestoreCollectionConfiguration {
    var collection: String
}

interface GCloudConfiguration {
    var projectId: String
}

@ConfigurationProperties("contact")
class ContactFirestoreCollection : FirestoreCollectionConfiguration {
    override var collection: String = "error"
}

@ConfigurationProperties("gcloud")
class DefaultGCloudConfiguration : GCloudConfiguration {
    override var projectId: String = "error"
}
