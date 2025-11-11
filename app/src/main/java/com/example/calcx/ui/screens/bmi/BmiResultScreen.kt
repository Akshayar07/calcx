package com.example.calcx.ui.screens.bmi

import AppButton
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Height
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material.icons.filled.Scale
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.calcx.ui.components.SectionTitle
import com.example.calcx.viewmodel.BmiViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BmiResultScreen(navController: NavHostController, bmiViewModel: BmiViewModel = viewModel()) {

    Scaffold { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = bmiViewModel.bmi.toString(),
                    style = TextStyle(
                        fontSize = 50.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                )
                Text(
                    modifier = Modifier, text = "BMI", style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                )
            }
            Card(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                ),
                modifier = Modifier,
                shape = RoundedCornerShape(30.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Green, contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = bmiViewModel.category,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                    )
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                InfoSection(
                    iconColor = Color.Blue,
                    icon = Icons.Default.Scale,
                    value = bmiViewModel.weight,
                    unit = "kg"
                )
                Spacer(modifier = Modifier.width(10.dp))
                InfoSection(
                    iconColor = Color.Green,
                    icon = Icons.Default.Height,
                    value = bmiViewModel.height,
                    unit = "cm"
                )
                Spacer(modifier = Modifier.width(10.dp))
                InfoSection(
                    iconColor = Color.Red,
                    icon = Icons.Default.PersonOutline,
                    value = bmiViewModel.age,
                    unit = "years"
                )
            }
            HealthTipSection(bmiViewModel.healthTip)
            BmiScaleSection()
            AppButton(onClick = {
                bmiViewModel.reset()
                navController.popBackStack()

            }, label = "Recalculate BMI")
        }
    }
}


@Composable
fun BmiScaleSection() {
    Column(
        modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row {
            Icon(
                imageVector = Icons.Default.Info, contentDescription = "Info"
            )
            SectionTitle(
                title = "BMI Scale", modifier = Modifier.padding(horizontal = 16.dp),
            )
        }
        BmiScaleItem(
            title = "Underweight", value = "< 18.5", color = Color.Blue
        )
        BmiScaleItem(
            title = "Normal weight", value = "18.5 - 24.9", color = Color.Green
        )
        BmiScaleItem(
            title = "Overweight", value = "25 - 29.9", color = Color(0xFFA52A2A)
        )
        BmiScaleItem(title = "Obesity", value = "> 30", color = Color.Red)
    }
}


@Composable
private fun RowScope.InfoSection(iconColor: Color, icon: ImageVector, value: String, unit: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .padding(vertical = 8.dp)

    ) {
        Box(
            contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()

        ) {
            Column(
                modifier = Modifier.padding(vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    tint = iconColor, imageVector = icon, contentDescription = "Favorite"
                )
                SectionTitle(title = value)
                Text(text = unit)
            }
        }
    }
}


@Composable
private fun HealthTipSection(healthTip: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFFC11C84).copy(alpha = 0.05f), shape = RoundedCornerShape(16.dp)
            )
    ) {
        Row(modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp)) {
            Icon(
                tint = Color(0xFFC11C84),
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = "Favorite"
            )
            Column {
                SectionTitle(
                    title = "Health Tip", modifier = Modifier.padding(horizontal = 8.dp),
                )
                Text(
                    text = healthTip,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
    }
}

@Composable
private fun BmiScaleItem(title: String, value: String, color: Color) {
    Box(
        modifier = Modifier.background(
            color = color.copy(alpha = 0.05f), shape = RoundedCornerShape(12.dp)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SectionTitle(
                title = title,
            )
            SectionTitle(
                title = value, color = color
            )
        }
    }
}