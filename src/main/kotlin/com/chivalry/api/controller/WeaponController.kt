package com.chivalry.api.controller

import com.chivalry.api.service.WeaponDto
import com.chivalry.api.service.WeaponService
import com.chivalry.api.service.WeaponSummaryDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/weapons")
class WeaponController(private val weaponService: WeaponService) {

    @GetMapping
    fun getAllWeapons(
        @RequestParam(required = false) category: String?,
        @RequestParam(required = false) damageType: String?
    ): List<WeaponSummaryDto> = when {
        category != null -> weaponService.getWeaponsByCategory(category)
        damageType != null -> weaponService.getWeaponsByDamageType(damageType)
        else -> weaponService.getAllWeapons()
    }

    @GetMapping("/{id}")
    fun getWeaponById(@PathVariable id: Long): ResponseEntity<WeaponDto> =
        weaponService.getWeaponById(id)
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @GetMapping("/name/{name}")
    fun getWeaponByName(@PathVariable name: String): ResponseEntity<WeaponDto> =
        weaponService.getWeaponByName(name)
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @GetMapping("/random")
    fun getRandomWeapon(): ResponseEntity<WeaponDto> =
        weaponService.getRandomWeapon()
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @GetMapping("/top")
    fun getTopDamageWeapons(@RequestParam(defaultValue = "10") limit: Int): List<WeaponSummaryDto> =
        weaponService.getTopDamageWeapons(limit)

    @GetMapping("/categories")
    fun getCategories(): List<String> = weaponService.getCategories()

    @GetMapping("/damage-types")
    fun getDamageTypes(): List<String> = weaponService.getDamageTypes()
}
