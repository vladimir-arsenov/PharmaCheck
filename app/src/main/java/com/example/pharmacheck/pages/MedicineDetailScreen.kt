package com.example.pharmacheck.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pharmacheck.entity.Medicine

@Composable
fun MedicineDetailScreen(medicine: Medicine, navController: NavController) {

    medicine.let {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = it.RusName ?: "Medicine", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = it.Interaction ?: " ")
        }
    }
}