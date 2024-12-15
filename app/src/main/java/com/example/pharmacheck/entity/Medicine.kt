package com.example.pharmacheck.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Medicine")
data class Medicine(
    @PrimaryKey val MedicineId: Int,
    val RusName: String?,
    val Indication: String?,
    val Interaction: String?,
    val StorageCondition: String?,
    val CompiledComposition: String?,
    val SideEffects: String?,
    val Dosage: String?)