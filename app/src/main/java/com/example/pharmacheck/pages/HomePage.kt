package com.example.pharmacheck.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.asLiveData
import androidx.navigation.NavController
import com.example.pharmacheck.dao.MedicineDao
import com.example.pharmacheck.entity.Medicine
import com.example.pharmacheck.viewmodel.AuthState
import com.example.pharmacheck.viewmodel.AuthViewModel
import kotlinx.coroutines.launch

@Composable
fun HomePage(
    medicineDao: MedicineDao,
    navController: NavController,
    authViewModel: AuthViewModel
) {

    val authState = authViewModel.authState.observeAsState()
    val scope = rememberCoroutineScope()
    var query by remember { mutableStateOf("") }
    var medicines by remember { mutableStateOf<List<Medicine>>(emptyList()) }

    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Unauthenticated -> navController.navigate("login")
            else -> Unit
        }
    }


    Column {
        BasicTextField(
            value = query,
            onValueChange = { query = it },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )
        Button(onClick = {
            scope.launch {
                medicines = medicineDao.searchMedicines(query).asLiveData().value ?: emptyList()
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