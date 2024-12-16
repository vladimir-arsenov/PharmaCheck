package com.example.pharmacheck.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pharmacheck.viewmodel.CompatibilityViewModel

@Composable
fun CompatibilityCheckScreen(navController: NavController, viewModel: CompatibilityViewModel) {
    val selectedMeds = viewModel.selectedMedicines

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("selectMedicine") }) {
                Text("+")
            }
        }
    ) {paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues)
            .padding(16.dp)) {
            Text("Выбранные лекарства")

            LazyColumn(modifier = Modifier.weight(1f)) {
                itemsIndexed(selectedMeds) { ind, medicine ->
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)) {
                        Row(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(medicine.RusName)
                            IconButton(onClick = { viewModel.removeMedicine(medicine) }) {
                                Icon(Icons.Default.Delete, contentDescription = "Удалить")
                            }
                        }
                    }
                }
            }

            Button(
                onClick = {
                    viewModel.checkCompatibility()
                    navController.navigate("compatibilityResult")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Проверить совместимость")
            }
        }
    }
}