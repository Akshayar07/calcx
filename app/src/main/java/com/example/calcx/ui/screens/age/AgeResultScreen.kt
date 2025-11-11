package com.example.calcx.ui.screens.age

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.calcx.viewmodel.AgeViewModel

@Composable
fun AgeResultScreen(navController: NavHostController, ageViewModel: AgeViewModel) {
    Scaffold() { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 40.dp, vertical = 10.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = buildString {
                    append("So, You Are ")
                    append(ageViewModel.years)
                    append(" Years Old")
                },
                textAlign = TextAlign.Center,
                style = TextStyle(fontWeight = FontWeight.W600, fontSize = 32.sp)
            )
            Spacer(modifier = Modifier.height(30.dp))
            Card() {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 30.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        "DETAILS",
                        style = TextStyle(fontWeight = FontWeight.W600, fontSize = 24.sp),
                        textDecoration = TextDecoration.Underline
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        "Years: ${ageViewModel.years}",
                        style = TextStyle(fontWeight = FontWeight.W600, fontSize = 18.sp)
                    )
                    Text(
                        "Month: ${ageViewModel.months}",
                        style = TextStyle(fontWeight = FontWeight.W600, fontSize = 18.sp)
                    )
                    Text(
                        "Days: ${ageViewModel.days}",
                        style = TextStyle(fontWeight = FontWeight.W600, fontSize = 18.sp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            IconButton(
                onClick = { navController.popBackStack() },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Icon(imageVector = Icons.Default.Refresh, contentDescription = "Back")

            }
        }
    }
}