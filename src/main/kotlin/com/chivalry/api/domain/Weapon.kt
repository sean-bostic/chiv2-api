package com.chivalry.api.domain

import jakarta.persistence.*

@Entity
@Table(name = "weapons")
data class Weapon(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(unique = true, nullable = false)
    val name: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val category: WeaponCategory,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val damageType: DamageType,

    val slashDamage: Int,
    val stabDamage: Int,
    val overheadDamage: Int,
    val specialDamage: Int,

    @Embedded
    @AttributeOverrides(
        AttributeOverride(name = "windup", column = Column(name = "slash_windup")),
        AttributeOverride(name = "windupHeavy", column = Column(name = "slash_windup_heavy")),
        AttributeOverride(name = "release", column = Column(name = "slash_release")),
        AttributeOverride(name = "recovery", column = Column(name = "slash_recovery"))
    )
    val slashTiming: AttackTiming,

    @Embedded
    @AttributeOverrides(
        AttributeOverride(name = "windup", column = Column(name = "stab_windup")),
        AttributeOverride(name = "windupHeavy", column = Column(name = "stab_windup_heavy")),
        AttributeOverride(name = "release", column = Column(name = "stab_release")),
        AttributeOverride(name = "recovery", column = Column(name = "stab_recovery"))
    )
    val stabTiming: AttackTiming,

    @Embedded
    @AttributeOverrides(
        AttributeOverride(name = "windup", column = Column(name = "overhead_windup")),
        AttributeOverride(name = "windupHeavy", column = Column(name = "overhead_windup_heavy")),
        AttributeOverride(name = "release", column = Column(name = "overhead_release")),
        AttributeOverride(name = "recovery", column = Column(name = "overhead_recovery"))
    )
    val overheadTiming: AttackTiming,

    @Embedded
    @AttributeOverrides(
        AttributeOverride(name = "windup", column = Column(name = "special_windup")),
        AttributeOverride(name = "windupHeavy", column = Column(name = "special_windup_heavy")),
        AttributeOverride(name = "release", column = Column(name = "special_release")),
        AttributeOverride(name = "recovery", column = Column(name = "special_recovery"))
    )
    val specialTiming: AttackTiming
) {
    val averageDamage: Double
        get() = (slashDamage + stabDamage + overheadDamage + specialDamage) / 4.0

    val maxDamage: Int
        get() = maxOf(slashDamage, stabDamage, overheadDamage, specialDamage)

    val fastestAttackWindup: Double
        get() = minOf(slashTiming.windup, stabTiming.windup, overheadTiming.windup)
}
