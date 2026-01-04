package com.chivalry.api.domain

import jakarta.persistence.Embeddable

@Embeddable
data class AttackTiming(
    val windup: Double = 0.0,
    val windupHeavy: Double = 0.0,
    val release: Double = 0.0,
    val recovery: Double = 0.0
) {
    val totalTime: Double get() = windup + release + recovery
    val totalTimeHeavy: Double get() = windupHeavy + release + recovery
}
