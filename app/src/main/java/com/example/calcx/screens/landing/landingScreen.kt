package com.example.calcx.screens.landing

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandingScreen() {
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
                .padding(8.dp)
        ) {
            Text(
                text = "Hello Akshaya Radhakrishnan", style = TextStyle(
                    fontSize = 24.sp, fontWeight = FontWeight.Bold
                )
            )
            Text(text = "Welcome Back!")

            Box(modifier = Modifier
                .weight(1f)
                .height(100.dp)) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize(),
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "Age")
                        }
                    }
                    Box(modifier = Modifier.weight(1f)) {


                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Card(modifier = Modifier.height(100.dp)) {
                                Box(
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .fillMaxSize(),

                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = "Bmi")
                                }
                            }
                            Card(modifier = Modifier.height(100.dp)) {
                                Box(
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .fillMaxSize(),

                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = "Bmi")
                                }
                            }


                        }
                    }

                    //


                }
            }

        }
    }

}

