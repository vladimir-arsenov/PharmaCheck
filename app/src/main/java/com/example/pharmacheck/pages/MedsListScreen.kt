package com.example.pharmacheck.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.asLiveData
import androidx.navigation.NavController
import com.example.pharmacheck.MedicineViewModel
import com.example.pharmacheck.dao.MedicineDao
import com.example.pharmacheck.entity.Medicine
import kotlinx.coroutines.launch

@Composable
fun MedsListScreen(viewModel: MedicineViewModel, medDao: MedicineDao, navController: NavController) {
    val scope = rememberCoroutineScope()
    var query by remember { mutableStateOf("") }
    var medicines by remember { mutableStateOf<List<Medicine>>(emptyList()) }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        BasicTextField(
            value = query,
            onValueChange = { query = it },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )
        Spacer(Modifier.height(16.dp))
        Button(onClick = {
            scope.launch {
                medicines = medDao.searchMedicines(query).asLiveData().value!!
            }
        }) {
            Text("Поиск")
        }

        LazyColumn {
            items(medicines) { med ->
                ListItem(
                    headlineContent = { Text(med.RusName ?: "Medicine") },
                    modifier = Modifier.clickable {
                        navController.navigate("medicineDetail/${med.MedicineId}")
                    }
                )
            }
        }
    }
}