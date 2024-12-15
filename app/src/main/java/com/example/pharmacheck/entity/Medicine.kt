package com.example.pharmacheck.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Medicine")
data class Medicine(
    @PrimaryKey val MedicineId: Int,
    val RusName: String?,
    val ComiledComposition: String?,
    val Dosage: String?,
    val Interaction: String?,
    val SideEffects: String?,
    val StorageComposition: String?,
    val Indication: String?
)