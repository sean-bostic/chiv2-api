package com.chivalry.api.config

import com.chivalry.api.domain.*
import com.chivalry.api.repository.WeaponRepository
import org.slf4j.LoggerFactory
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class DataLoader(private val weaponRepository: WeaponRepository) : ApplicationRunner {

    private val log = LoggerFactory.getLogger(javaClass)

    @Transactional
    override fun run(args: ApplicationArguments) {
        if (weaponRepository.count() > 0) {
            log.info("Database already populated with ${weaponRepository.count()} weapons")
            return
        }

        log.info("Loading weapon data...")
        val weapons = createWeapons()
        weaponRepository.saveAll(weapons)
        log.info("Loaded ${weapons.size} weapons into database")
    }

    private fun createWeapons(): List<Weapon> = listOf(
        weapon("Greatsword", WeaponCategory.TWO_HANDED_SWORDS, DamageType.CUT, 50, 50, 70, 100,
            timing(0.325, 0.675, 0.550, 1.10), timing(0.350, 0.700, 0.425, 1.00),
            timing(0.325, 0.675, 0.525, 1.10), timing(1.00, 1.00, 0.45, 1.35)),
        weapon("Highland Sword", WeaponCategory.TWO_HANDED_SWORDS, DamageType.CUT, 50, 40, 70, 55,
            timing(0.425, 0.625, 0.650, 1.40), timing(0.400, 0.750, 0.450, 1.50),
            timing(0.475, 0.825, 0.650, 1.40), timing(0.90, 0.90, 0.80, 1.50)),
        weapon("Longsword", WeaponCategory.TWO_HANDED_SWORDS, DamageType.CUT, 40, 55, 50, 80,
            timing(0.225, 0.575, 0.475, 0.80), timing(0.275, 0.625, 0.400, 0.80),
            timing(0.200, 0.550, 0.450, 0.80), timing(0.85, 0.85, 0.45, 1.20)),
        weapon("Messer", WeaponCategory.TWO_HANDED_SWORDS, DamageType.CUT, 55, 40, 70, 90,
            timing(0.325, 0.675, 0.525, 0.95), timing(0.300, 0.650, 0.425, 0.95),
            timing(0.325, 0.675, 0.500, 0.95), timing(0.90, 0.90, 0.45, 1.30)),
        weapon("Halberd", WeaponCategory.HALBERDS, DamageType.CHOP, 45, 55, 55, 85,
            timing(0.350, 0.700, 0.600, 1.05), timing(0.400, 0.750, 0.370, 1.00),
            timing(0.350, 0.700, 0.500, 1.05), timing(1.05, 1.05, 0.60, 1.30)),
        weapon("Pole Axe", WeaponCategory.HALBERDS, DamageType.CHOP, 40, 50, 50, 70,
            timing(0.350, 0.700, 0.500, 0.85), timing(0.350, 0.700, 0.350, 0.85),
            timing(0.300, 0.650, 0.500, 0.85), timing(1.00, 1.00, 0.60, 1.20)),
        weapon("Glaive", WeaponCategory.HALBERDS, DamageType.CHOP, 30, 40, 40, 60,
            timing(0.200, 0.550, 0.550, 0.85), timing(0.400, 0.750, 0.350, 0.85),
            timing(0.200, 0.550, 0.475, 0.85), timing(0.90, 0.90, 0.55, 1.10)),
        weapon("Dane Axe", WeaponCategory.AXES, DamageType.CHOP, 50, 40, 60, 85,
            timing(0.275, 0.625, 0.500, 0.80), timing(0.300, 0.650, 0.400, 0.80),
            timing(0.250, 0.600, 0.475, 0.80), timing(0.85, 0.85, 0.50, 1.00)),
        weapon("Battle Axe", WeaponCategory.AXES, DamageType.CHOP, 55, 40, 65, 100,
            timing(0.3255, 0.6755, 0.550, 0.90), timing(0.350, 0.700, 0.375, 0.90),
            timing(0.350, 0.700, 0.525, 0.95), timing(1.00, 1.00, 0.50, 1.30)),
        weapon("War Axe", WeaponCategory.AXES, DamageType.CHOP, 50, 35, 60, 90,
            timing(0.3255, 0.6755, 0.550, 1.00), timing(0.350, 0.700, 0.400, 1.00),
            timing(0.325, 0.675, 0.500, 1.00), timing(1.10, 1.10, 0.50, 1.35)),
        weapon("Executioners Axe", WeaponCategory.AXES, DamageType.CHOP, 45, 30, 60, 80,
            timing(0.3255, 0.6755, 0.600, 1.10), timing(0.350, 0.700, 0.400, 1.10),
            timing(0.325, 0.675, 0.550, 1.10), timing(1.10, 1.10, 0.50, 1.35)),
        weapon("Heavy Mace", WeaponCategory.MACES, DamageType.BLUNT, 40, 40, 50, 80,
            timing(0.250, 0.600, 0.550, 0.95), timing(0.300, 0.650, 0.350, 0.95),
            timing(0.300, 0.650, 0.550, 0.95), timing(1.00, 1.00, 0.50, 1.30)),
        weapon("War Club", WeaponCategory.MACES, DamageType.BLUNT, 30, 35, 45, 65,
            timing(0.200, 0.550, 0.500, 0.80), timing(0.300, 0.650, 0.350, 0.80),
            timing(0.200, 0.550, 0.500, 0.80), timing(0.85, 0.85, 0.50, 1.10)),
        weapon("Two Handed Hammer", WeaponCategory.MACES, DamageType.BLUNT, 40, 35, 50, 80,
            timing(0.275, 0.625, 0.525, 0.90), timing(0.300, 0.650, 0.350, 0.90),
            timing(0.325, 0.675, 0.500, 0.90), timing(1.10, 1.10, 0.50, 1.30)),
        weapon("Maul", WeaponCategory.MACES, DamageType.BLUNT, 50, 30, 60, 120,
            timing(0.375, 0.725, 0.550, 1.15), timing(0.350, 0.700, 0.375, 0.90),
            timing(0.450, 0.800, 0.525, 1.15), timing(1.35, 1.35, 0.50, 1.35)),
        weapon("Polehammer", WeaponCategory.MACES, DamageType.BLUNT, 45, 40, 50, 100,
            timing(0.300, 0.650, 0.575, 1.00), timing(0.300, 0.650, 0.400, 1.00),
            timing(0.350, 0.700, 0.525, 1.00), timing(1.10, 1.10, 0.60, 1.35)),
        weapon("Spear", WeaponCategory.SPEARS, DamageType.CUT, 40, 50, 30, 70,
            timing(0.350, 0.700, 0.350, 0.90), timing(0.350, 0.700, 0.350, 0.90),
            timing(0.250, 0.600, 0.450, 0.85), timing(0.70, 0.70, 1.00, 1.00)),
        weapon("Javelin", WeaponCategory.SPEARS, DamageType.CUT, 30, 40, 35, 50,
            timing(0.250, 0.600, 0.350, 0.85), timing(0.200, 0.550, 0.300, 0.85),
            timing(0.250, 0.600, 0.425, 0.80), timing(0.70, 0.70, 0.35, 0.90)),
        weapon("Lance", WeaponCategory.SPEARS, DamageType.CUT, 40, 50, 25, 50,
            timing(0.300, 0.650, 0.350, 0.90), timing(0.350, 0.700, 0.350, 0.90),
            timing(0.250, 0.600, 0.450, 0.90), timing(1.00, 1.00, 1.00, 1.00)),
        weapon("Sword", WeaponCategory.ONE_HANDED, DamageType.CUT, 40, 45, 50, 70,
            timing(0.150, 0.500, 0.425, 0.75), timing(0.225, 0.575, 0.300, 0.75),
            timing(0.150, 0.500, 0.400, 0.75), timing(0.85, 0.85, 0.35, 0.95)),
        weapon("Falchion", WeaponCategory.ONE_HANDED, DamageType.CUT, 50, 40, 55, 80,
            timing(0.200, 0.550, 0.475, 0.80), timing(0.200, 0.550, 0.300, 0.80),
            timing(0.200, 0.550, 0.450, 0.80), timing(0.90, 0.90, 0.35, 1.00)),
        weapon("Axe", WeaponCategory.ONE_HANDED, DamageType.CHOP, 50, 35, 55, 75,
            timing(0.175, 0.525, 0.450, 0.85), timing(0.200, 0.550, 0.350, 0.90),
            timing(0.175, 0.525, 0.450, 0.85), timing(0.85, 0.85, 0.45, 0.85)),
        weapon("Hatchet", WeaponCategory.ONE_HANDED, DamageType.CHOP, 40, 30, 55, 70,
            timing(0.125, 0.475, 0.450, 0.80), timing(0.200, 0.550, 0.350, 0.85),
            timing(0.125, 0.475, 0.450, 0.80), timing(0.80, 0.80, 0.45, 0.90)),
        weapon("Cudgel", WeaponCategory.ONE_HANDED, DamageType.BLUNT, 30, 25, 30, 55,
            timing(0.125, 0.475, 0.400, 0.70), timing(0.200, 0.550, 0.300, 0.70),
            timing(0.125, 0.475, 0.400, 0.70), timing(0.80, 0.80, 0.45, 0.90)),
        weapon("Short Sword", WeaponCategory.ONE_HANDED, DamageType.SLASH, 35, 40, 40, 65,
            timing(0.125, 0.475, 0.400, 0.65), timing(0.225, 0.575, 0.300, 0.65),
            timing(0.125, 0.475, 0.400, 0.65), timing(0.80, 0.80, 0.35, 0.90)),
        weapon("Mace", WeaponCategory.ONE_HANDED, DamageType.BLUNT, 40, 35, 40, 65,
            timing(0.150, 0.500, 0.475, 0.75), timing(0.175, 0.525, 0.300, 0.80),
            timing(0.150, 0.500, 0.450, 0.80), timing(0.85, 0.85, 0.45, 0.95)),
        weapon("Morning Star", WeaponCategory.ONE_HANDED, DamageType.BLUNT, 40, 35, 40, 65,
            timing(0.150, 0.500, 0.475, 0.75), timing(0.200, 0.550, 0.300, 0.80),
            timing(0.150, 0.500, 0.450, 0.80), timing(0.85, 0.85, 0.45, 0.95)),
        weapon("Warhammer", WeaponCategory.ONE_HANDED, DamageType.BLUNT, 45, 30, 50, 80,
            timing(0.200, 0.550, 0.500, 0.90), timing(0.200, 0.550, 0.300, 0.90),
            timing(0.150, 0.500, 0.500, 0.90), timing(1.00, 1.00, 0.45, 1.15)),
        weapon("One Handed Spear", WeaponCategory.ONE_HANDED, DamageType.CUT, 40, 50, 30, 80,
            timing(0.250, 0.600, 0.375, 0.90), timing(0.240, 0.590, 0.300, 0.85),
            timing(0.250, 0.600, 0.450, 0.90), timing(0.80, 0.80, 0.50, 1.15)),
        weapon("Rapier", WeaponCategory.ONE_HANDED, DamageType.CUT, 30, 50, 35, 55,
            timing(0.120, 0.470, 0.425, 0.75), timing(0.200, 0.550, 0.300, 0.75),
            timing(0.120, 0.470, 0.400, 0.75), timing(0.60, 0.60, 0.35, 0.95)),
        weapon("Pickaxe", WeaponCategory.ONE_HANDED, DamageType.CHOP, 40, 35, 50, 70,
            timing(0.125, 0.475, 0.450, 0.80), timing(0.175, 0.525, 0.300, 0.85),
            timing(0.125, 0.475, 0.425, 0.80), timing(0.80, 0.80, 0.45, 1.00)),
        weapon("Shovel", WeaponCategory.ONE_HANDED, DamageType.BLUNT, 30, 45, 45, 70,
            timing(0.175, 0.525, 0.475, 0.80), timing(0.175, 0.525, 0.300, 0.85),
            timing(0.175, 0.525, 0.425, 0.80), timing(0.85, 0.85, 0.50, 1.00)),
        weapon("Sledgehammer", WeaponCategory.ONE_HANDED, DamageType.BLUNT, 40, 30, 50, 70,
            timing(0.200, 0.550, 0.525, 0.90), timing(0.200, 0.550, 0.300, 0.90),
            timing(0.200, 0.550, 0.450, 0.90), timing(0.95, 0.95, 0.50, 1.10))
    )

    private fun weapon(
        name: String, category: WeaponCategory, damageType: DamageType,
        slash: Int, stab: Int, overhead: Int, special: Int,
        slashTiming: AttackTiming, stabTiming: AttackTiming,
        overheadTiming: AttackTiming, specialTiming: AttackTiming
    ) = Weapon(
        name = name, category = category, damageType = damageType,
        slashDamage = slash, stabDamage = stab, overheadDamage = overhead, specialDamage = special,
        slashTiming = slashTiming, stabTiming = stabTiming,
        overheadTiming = overheadTiming, specialTiming = specialTiming
    )

    private fun timing(windup: Double, windupHeavy: Double, release: Double, recovery: Double) =
        AttackTiming(windup, windupHeavy, release, recovery)
}
