package xyz.nicholasq.jss.domain.pipeline

import xyz.nicholasq.jss.infrastructure.entity.Entity

interface PipelineEntity<K> : Entity<K> {
    var name: String
    var description: String?
}

interface PhaseEntity<K> : Entity<K> {
    var name: String
    var pipelineId: K
    var description: String?
}

interface JobEntity<K> : Entity<K> {
    var name: String
    var pipelineId: K
    var phaseId: K
    var description: String?
}
