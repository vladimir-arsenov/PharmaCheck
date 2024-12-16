package com.example.pharmacheck.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.pharmacheck.entity.Medicine
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicineDao {

    // Получить все лекарства
    @Query("SELECT * FROM Medicine")
    fun getAllMedicines(): Flow<List<Medicine>>

    // Поиск лекарств по названию или действующему веществу
    @Query("""
        SELECT * FROM Medicine 
        WHERE RusName LIKE :query
    """)
    fun searchMedicines(query: String): Flow<List<Medicine>>

    // Получить взаимодействия лекарств по их ID
    @Query("""
        SELECT Interaction FROM Medicine 
        WHERE MedicineId IN (:medicineIds)
    """)
    suspend fun getMedicineInteractions(medicineIds: List<String>): List<String>

    @Query("""
        SELECT * FROM Medicine
        WHERE MedicineId = :medicineId
    """)
    fun getMedicineById(medicineId: Int) : Medicine
}