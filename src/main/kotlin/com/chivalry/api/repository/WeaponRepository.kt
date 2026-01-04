package com.chivalry.api.repository

import com.chivalry.api.domain.DamageType
import com.chivalry.api.domain.Weapon
import com.chivalry.api.domain.WeaponCategory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface WeaponRepository : JpaRepository<Weapon, Long> {

    fun findByNameIgnoreCase(name: String): Weapon?

    fun findByCategory(category: WeaponCategory): List<Weapon>

    fun findByDamageType(damageType: DamageType): List<Weapon>

    fun findByCategoryAndDamageType(category: WeaponCategory, damageType: DamageType): List<Weapon>

    @Query("SELECT w FROM Weapon w ORDER BY RANDOM()")
    fun findRandom(): List<Weapon>

    @Query("SELECT w FROM Weapon w ORDER BY (w.slashDamage + w.stabDamage + w.overheadDamage + w.specialDamage) DESC")
    fun findAllOrderByTotalDamageDesc(): List<Weapon>
}
