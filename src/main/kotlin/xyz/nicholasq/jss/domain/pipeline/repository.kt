package xyz.nicholasq.jss.domain.pipeline

import jakarta.inject.Named
import jakarta.inject.Singleton
import xyz.nicholasq.jss.infrastructure.data.FirestoreCollectionConfiguration
import xyz.nicholasq.jss.infrastructure.data.GCloudConfiguration

@Singleton
class PipelineRepository<K>(
    gcloudConfig: GCloudConfiguration,
    @Named("pipeline") firestoreCollectionConfig: FirestoreCollectionConfiguration
)

