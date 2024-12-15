package com.example.pharmacheck.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pharmacheck.viewmodel.MedicineViewModel
import com.example.pharmacheck.entity.Medicine
import com.example.pharmacheck.viewmodel.AuthState
import com.example.pharmacheck.viewmodel.AuthViewModel
import kotlinx.coroutines.launch

@Composable
fun HomePage(
    navController: NavController,
    authViewModel: AuthViewModel,
    medicineViewModel: MedicineViewModel
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


    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (Modifier.fillMaxWidth()) {
            BasicTextField(
                value = query,
                onValueChange = { query = it },
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            )
            Button(onClick = {
                scope.launch {
                    medicineViewModel.searchMedicines(query)
                }
            }) {
                Text("Поиск")
            }
        }
        Spacer(Modifier.height(16.dp))
        LazyColumn {
            itemsIndexed(medicines) { index, med ->
                Card(
                    onClick = {
                        navController.navigate("medicineDetail/${med.MedicineId}")
                    },
                    modifier = Modifier.padding(8.dp),
                    elevation = CardDefaults.elevatedCardElevation()
                ) {
                        Text(
                            text = medicines[index].RusName ?: "Medicine",
                            modifier = Modifier.padding(4.dp),
                            color = Color.Black, textAlign = TextAlign.Center
                        )
                    }
                }
        }
    }

}