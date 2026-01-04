package com.chivalry.api.service

import com.chivalry.api.domain.AttackTiming
import com.chivalry.api.domain.Weapon

data class AttackTimingDto(
    val windup: Double,
    val windupHeavy: Double,
    val release: Double,
    val recovery: Double,
    val totalTime: Double,
    val totalTimeHeavy: Double
)

data class WeaponDto(
    val id: Long,
    val name: String,
    val category: String,
    val damageType: String,
    val damage: DamageStats,
    val timing: TimingStats
)

data class DamageStats(
    val slash: Int,
    val stab: Int,
    val overhead: Int,
    val special: Int,
    val average: Double,
    val max: Int
)

data class TimingStats(
    val slash: AttackTimingDto,
    val stab: AttackTimingDto,
    val overhead: AttackTimingDto,
    val special: AttackTimingDto,
    val fastestWindup: Double
)

data class WeaponSummaryDto(
    val id: Long,
    val name: String,
    val category: String,
    val damageType: String,
    val averageDamage: Double,
    val maxDamage: Int
)

fun AttackTiming.toDto() = AttackTimingDto(
    windup = windup,
    windupHeavy = windupHeavy,
    release = release,
    recovery = recovery,
    totalTime = totalTime,
    totalTimeHeavy = totalTimeHeavy
)

fun Weapon.toDto() = WeaponDto(
    id = id,
    name = name,
    category = category.toDisplayName(),
    damageType = damageType.name.lowercase().replaceFirstChar { it.uppercase() },
    damage = DamageStats(
        slash = slashDamage,
        stab = stabDamage,
        overhead = overheadDamage,
        special = specialDamage,
        average = averageDamage,
        max = maxDamage
    ),
    timing = TimingStats(
        slash = slashTiming.toDto(),
        stab = stabTiming.toDto(),
        overhead = overheadTiming.toDto(),
        special = specialTiming.toDto(),
        fastestWindup = fastestAttackWindup
    )
)

fun Weapon.toSummaryDto() = WeaponSummaryDto(
    id = id,
    name = name,
    category = category.toDisplayName(),
    damageType = damageType.name.lowercase().replaceFirstChar { it.uppercase() },
    averageDamage = averageDamage,
    maxDamage = maxDamage
)
