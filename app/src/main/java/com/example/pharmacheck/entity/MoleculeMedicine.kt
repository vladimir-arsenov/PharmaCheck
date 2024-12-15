package com.example.pharmacheck.entity

import androidx.room.Entity

@Entity(tableName = "Molecule_Medicine", primaryKeys = ["MoleculeID", "MedicineId"])
data class MoleculeMedicine(
    val MoleculeID: Int,
    val MedicineId: Int
)