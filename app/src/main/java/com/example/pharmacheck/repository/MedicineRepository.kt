package com.example.pharmacheck.repository

import com.example.pharmacheck.dao.MedicineDao
import com.example.pharmacheck.entity.Medicine
import kotlinx.coroutines.flow.Flow

class MedicineRepository(private val medicineDao: MedicineDao) {

    // Получить все лекарства
    fun getAllMedicines(): Flow<List<Medicine>> {
        return medicineDao.getAllMedicines()
    }

    // Найти лекарства по названию или действующему веществу
    fun searchMedicines(query: String): Flow<List<Medicine>> {
        return medicineDao.searchMedicines("%$query%")
    }

    // Получить информацию о взаимодействиях лекарств
    suspend fun getInteractions(medicineIds: List<String>): List<String> {
        return medicineDao.getMedicineInteractions(medicineIds)
    }

    fun getMedicineById(medicineId: Int) : Medicine {
        return medicineDao.getMedicineById(medicineId)
    }
}