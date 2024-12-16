package com.example.pharmacheck.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pharmacheck.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, authViewModel: AuthViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Главное меню") })
        }
    ) { pv ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(pv)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { navController.navigate("compatibilityCheck") },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Text("Проверка совместимости лекарств")
            }

            Button(
                onClick = { navController.navigate("medicineList") },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Text("Просмотр информации о лекарствах")
            }

            Button(
                onClick = {
                    authViewModel.signout()
                    navController.navigate("login") },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Text("Выйти")
            }
        }
    }
}