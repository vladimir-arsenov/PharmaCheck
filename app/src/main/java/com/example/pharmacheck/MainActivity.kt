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
import com.example.pharmacheck.entity.Medicine
import com.example.pharmacheck.pages.HomePage
import com.example.pharmacheck.pages.LoginPage
import com.example.pharmacheck.pages.MedicineDetailScreen
import com.example.pharmacheck.pages.SignupPage
import com.example.pharmacheck.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val authViewModel : AuthViewModel by viewModels()
        val viewModel : MedicineViewModel by viewModels()
        val database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "app_database")
            .createFromAsset("data.db")
            .build()
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "login") {
                composable("login"){ LoginPage(navController,authViewModel) }
                composable("signup"){ SignupPage(navController, authViewModel) }
                composable("home"){ HomePage(database.medicineDao(), navController,authViewModel) }
//                composable("medsList") { MedsListScreen(viewModel, database.medicineDao(), navController) }
                composable("medicineDetail/{medicineId}") { backStackEntry ->
                    val medicineId = backStackEntry.arguments?.getString("medicineId")?.toInt()
                    val medicine : Medicine? = medicineId?.let { viewModel.getMedicineById(it) }
                    if (medicine == null)
                        Toast.makeText(applicationContext, "No info found", Toast.LENGTH_LONG).show()
                    else
                        MedicineDetailScreen(medicine, navController)
                }
            }
        }
    }
}