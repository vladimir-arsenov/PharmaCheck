package com.example.pharmacheck.entity;

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Molecule")
data class Molecule(
        @PrimaryKey val MoleculeID: Int,
        val RusName: String?,
        val LatName: String?
)