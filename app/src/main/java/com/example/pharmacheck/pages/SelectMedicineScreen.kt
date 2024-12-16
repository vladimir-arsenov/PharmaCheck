package com.example.pharmacheck.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pharmacheck.viewmodel.CompatibilityViewModel
import com.example.pharmacheck.viewmodel.MedicineViewModel

@Composable
fun SelectMedicineScreen(navController: NavController, compatibilityViewModel: CompatibilityViewModel, medicineicineViewModel: MedicineViewModel) {
    var searchQuery by remember { mutableStateOf("") }


    val filteredMedicines = medicineicineViewModel.allMedicines.value?.filter {
        it.RusName.contains(searchQuery, ignoreCase = true)
    }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Поиск лекарств") },
            modifier = Modifier.fillMaxWidth()
        )

        LazyColumn {
            itemsIndexed(filteredMedicines ?: emptyList()) { ind, medicine ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            compatibilityViewModel.addMedicine(medicine)
                            navController.popBackStack()
                        }
                ) {
                    Row(modifier = Modifier.padding(16.dp)) {
                        Text(medicine.RusName)
                    }
                }
            }
        }
    }
}