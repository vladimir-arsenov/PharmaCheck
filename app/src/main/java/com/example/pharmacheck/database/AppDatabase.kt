package com.example.pharmacheck.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pharmacheck.dao.MedicineDao
import com.example.pharmacheck.entity.Medicine

@Database(entities = [Medicine::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun medicineDao(): MedicineDao
}