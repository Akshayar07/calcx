package com.example.calcx.ui.theme.screens.landing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.calcx.ui.theme.navigation.Routes


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandingScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "CalcX")
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorite")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            GreetingText()
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .height(200.dp)
            ) {
                BuildCard(
                    title = "Age Calculator",
                    modifier = Modifier.weight(1f),
                    colorValue = 0xFFBFF6C3,
                    onClick = {
                        navController.navigate(Routes.AGE)
                    }
                )
                Box(modifier = Modifier.weight(1f)) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        BuildCard(
                            title = "Bmi Calculator",
                            modifier = Modifier.weight(1f),
                            colorValue = 0xFF9AD0EC,
                            onClick = {
                                navController.navigate(Routes.BMI)
                            }
                        )
                        BuildCard(
                            title = "Discount Calculator",
                            modifier = Modifier.weight(1f),
                            colorValue = 0xFFE5D9F2,
                            onClick = {
//                                navController.navigate(Routes.AGE)

                            }
                        )

                    }
                }
            }
        }
    }
}

@Composable
fun BuildCard(title: String, modifier: Modifier, colorValue: Long, onClick: () -> Unit) {
    val titleTextStyle = TextStyle(
        fontSize = 16.sp, fontWeight = FontWeight.Bold
    )
    Card(
        onClick = onClick,
        modifier = modifier, colors = CardDefaults.cardColors(
            containerColor = Color(colorValue),
        )
    ) {
        Box(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title, style = titleTextStyle
            )
        }
    }
}

@Composable
private fun GreetingText() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Hello Akshaya Radhakrishnan", style = TextStyle(
                fontSize = 24.sp, fontWeight = FontWeight.Bold
            )
        )
        Text(text = "Welcome Back!")
    }
}

