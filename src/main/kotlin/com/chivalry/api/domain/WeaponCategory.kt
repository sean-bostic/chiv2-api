package com.chivalry.api.domain

enum class WeaponCategory {
    TWO_HANDED_SWORDS,
    HALBERDS,
    AXES,
    MACES,
    SPEARS,
    ONE_HANDED;

    companion object {
        fun fromDisplayName(name: String): WeaponCategory = when (name.lowercase()) {
            "2-handed swords" -> TWO_HANDED_SWORDS
            "halberds" -> HALBERDS
            "axes" -> AXES
            "maces" -> MACES
            "spears" -> SPEARS
            "1-handed" -> ONE_HANDED
            else -> throw IllegalArgumentException("Unknown category: $name")
        }
    }

    fun toDisplayName(): String = when (this) {
        TWO_HANDED_SWORDS -> "2-Handed Swords"
        HALBERDS -> "Halberds"
        AXES -> "Axes"
        MACES -> "Maces"
        SPEARS -> "Spears"
        ONE_HANDED -> "1-Handed"
    }
}
