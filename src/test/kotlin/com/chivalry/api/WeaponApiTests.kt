package com.chivalry.api

import com.chivalry.api.repository.WeaponRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@SpringBootTest
@AutoConfigureMockMvc
class WeaponApiTests {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var weaponRepository: WeaponRepository

    @Test
    fun `data loader populates weapons`() {
        assertEquals(33, weaponRepository.count())
    }

    @Test
    fun `GET weapons returns all weapons`() {
        mockMvc.get("/api/weapons")
            .andExpect {
                status { isOk() }
                jsonPath("$.length()") { value(33) }
            }
    }

    @Test
    fun `GET weapon by id returns weapon details`() {
        val weapon = weaponRepository.findByNameIgnoreCase("Longsword")!!
        
        mockMvc.get("/api/weapons/${weapon.id}")
            .andExpect {
                status { isOk() }
                jsonPath("$.name") { value("Longsword") }
                jsonPath("$.category") { value("2-Handed Swords") }
                jsonPath("$.damage.slash") { value(40) }
            }
    }

    @Test
    fun `GET weapon by name returns weapon details`() {
        mockMvc.get("/api/weapons/name/Greatsword")
            .andExpect {
                status { isOk() }
                jsonPath("$.name") { value("Greatsword") }
                jsonPath("$.damage.special") { value(100) }
            }
    }

    @Test
    fun `GET weapons by category filters correctly`() {
        mockMvc.get("/api/weapons?category=Axes")
            .andExpect {
                status { isOk() }
                jsonPath("$.length()") { value(4) }
            }
    }

    @Test
    fun `GET random weapon returns a weapon`() {
        mockMvc.get("/api/weapons/random")
            .andExpect {
                status { isOk() }
                jsonPath("$.id") { exists() }
                jsonPath("$.name") { exists() }
            }
    }

    @Test
    fun `GET categories returns all weapon categories`() {
        mockMvc.get("/api/weapons/categories")
            .andExpect {
                status { isOk() }
                jsonPath("$.length()") { value(6) }
            }
    }
}
