package com.example.pharmacheck

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.pharmacheck.database.AppDatabase
import com.example.pharmacheck.entity.Medicine
import com.example.pharmacheck.pages.CompatibilityCheckScreen
import com.example.pharmacheck.pages.CompatibilityResultScreen
import com.example.pharmacheck.pages.HomeScreen
import com.example.pharmacheck.pages.LoginScreen
import com.example.pharmacheck.pages.MedicineDetailScreen
import com.example.pharmacheck.pages.MedicineListScreen
import com.example.pharmacheck.pages.SelectMedicineScreen
import com.example.pharmacheck.pages.SignupScreen
import com.example.pharmacheck.repository.MedicineRepository
import com.example.pharmacheck.viewmodel.AuthViewModel
import com.example.pharmacheck.viewmodel.CompatibilityViewModel
import com.example.pharmacheck.viewmodel.MedicineViewModel
import com.example.pharmacheck.viewmodel.MedicineViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val authViewModel : AuthViewModel by viewModels()
        authViewModel.signout()
        val database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "app_database")
            .createFromAsset("data.db")
            .build()

        val repository = MedicineRepository(database.medicineDao())

        val medicineViewModel : MedicineViewModel by viewModels { MedicineViewModelFactory(repository) }

        val compatibilityViewModel : CompatibilityViewModel by viewModels()

        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "home") {
                composable("login"){ LoginScreen(navController,authViewModel) }
                composable("signup"){ SignupScreen(navController, authViewModel) }
                composable("home") { HomeScreen(navController, authViewModel) }
                composable("medicineList"){ MedicineListScreen(navController, authViewModel, medicineViewModel) }
                composable("compatibilityCheck") { CompatibilityCheckScreen(navController, compatibilityViewModel) }
                composable("selectMedicine") { SelectMedicineScreen(navController, compatibilityViewModel, medicineViewModel) }
                composable("compatibilityResult") { CompatibilityResultScreen(navController, compatibilityViewModel) }
                composable("medicineDetail/{medicineId}") { backStackEntry ->
                    val medicineId = backStackEntry.arguments?.getString("medicineId")?.toInt()
                    val medicine : Medicine? = medicineId?.let { medicineViewModel.getMedicineById(it) }
                    if (medicine == null)
                        Toast.makeText(applicationContext, "No info found", Toast.LENGTH_LONG).show()
                    else
                        MedicineDetailScreen(medicine, navController)
                }
            }
        }
    }
}