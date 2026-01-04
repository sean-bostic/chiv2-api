package com.chivalry.api.service

import com.chivalry.api.domain.DamageType
import com.chivalry.api.domain.WeaponCategory
import com.chivalry.api.repository.WeaponRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class WeaponService(private val weaponRepository: WeaponRepository) {

    fun getAllWeapons(): List<WeaponSummaryDto> =
        weaponRepository.findAll().map { it.toSummaryDto() }

    fun getWeaponById(id: Long): WeaponDto? =
        weaponRepository.findById(id).orElse(null)?.toDto()

    fun getWeaponByName(name: String): WeaponDto? =
        weaponRepository.findByNameIgnoreCase(name)?.toDto()

    fun getWeaponsByCategory(category: String): List<WeaponSummaryDto> {
        val weaponCategory = try {
            WeaponCategory.fromDisplayName(category)
        } catch (e: IllegalArgumentException) {
            WeaponCategory.valueOf(category.uppercase().replace("-", "_").replace(" ", "_"))
        }
        return weaponRepository.findByCategory(weaponCategory).map { it.toSummaryDto() }
    }

    fun getWeaponsByDamageType(damageType: String): List<WeaponSummaryDto> {
        val type = DamageType.valueOf(damageType.uppercase())
        return weaponRepository.findByDamageType(type).map { it.toSummaryDto() }
    }

    fun getRandomWeapon(): WeaponDto? =
        weaponRepository.findRandom().firstOrNull()?.toDto()

    fun getTopDamageWeapons(limit: Int = 10): List<WeaponSummaryDto> =
        weaponRepository.findAllOrderByTotalDamageDesc()
            .take(limit)
            .map { it.toSummaryDto() }

    fun getCategories(): List<String> =
        WeaponCategory.entries.map { it.toDisplayName() }

    fun getDamageTypes(): List<String> =
        DamageType.entries.map { it.name.lowercase().replaceFirstChar { c -> c.uppercase() } }
}
