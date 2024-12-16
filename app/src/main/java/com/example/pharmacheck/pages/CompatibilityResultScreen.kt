package com.example.pharmacheck.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pharmacheck.viewmodel.CompatibilityViewModel

@Composable
fun CompatibilityResultScreen(navController: NavController, viewModel: CompatibilityViewModel) {
    val result by viewModel.compatibilityResult.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = result ?: "Результат отсутствует",
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            viewModel.resetResult()
            navController.popBackStack("compatibilityCheck", inclusive = false)
        }) {
            Text("Назад")
        }
    }
}