package com.example.pharmacheck

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pharmacheck.dao.MedicineDao
import com.example.pharmacheck.entity.Medicine
import com.example.pharmacheck.entity.Molecule
import com.example.pharmacheck.entity.MoleculeMedicine

@Database(entities = [Medicine::class, Molecule::class, MoleculeMedicine::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun medicineDao(): MedicineDao
}