package com.example.calcx.ui.theme.screens.bmi

import AppButton
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.calcx.model.Gender
import com.example.calcx.ui.theme.components.GenderOptionButton
import com.example.calcx.ui.theme.components.LabeledInputField
import com.example.calcx.ui.theme.components.SectionTitle
import com.example.calcx.ui.theme.navigation.Routes
import com.example.calcx.viewmodel.BmiViewModel

@Composable
fun BmiCalculationScreen(
    navController: NavHostController,
    bmiViewModel: BmiViewModel = viewModel(),
) {
    var selectedGender: Gender by bmiViewModel.selectedGender
    var weight: String by bmiViewModel.weight
    var height: String by bmiViewModel.height
    var age: String by bmiViewModel.age
    var bmi: Double by bmiViewModel.bmi
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            SectionTitle(
                title = "Gender",
                modifier = Modifier.padding(top = 24.dp, bottom = 8.dp),
            )
            GenderSelection(bmiViewModel = bmiViewModel, selectedGender = selectedGender)
            SectionTitle(
                title = "Weight (kg)",
                modifier = Modifier.padding(top = 24.dp, bottom = 8.dp),
            )
            LabeledInputField(
                value = weight.toString(),
                label = "Enter Your Weight",
                onValueChange = { weight = it })
            SectionTitle(
                title = "Height (cm)",
                modifier = Modifier.padding(top = 24.dp, bottom = 8.dp),
            )
            LabeledInputField(
                value = height, label = "Enter Your Height", onValueChange = { height = it })
            SectionTitle(
                title = "Age",
                modifier = Modifier.padding(top = 24.dp, bottom = 8.dp),

                )
            LabeledInputField(
                value = age, label = "Enter Your Age", onValueChange = { age = it })
            Spacer(modifier = Modifier.height(24.dp))
            AppButton(
                label = "Calculate BMI",
                onClick = {
                    navController.navigate(Routes.BMI_RESULT)
                    bmiViewModel.calculateBmi()

                })
            Text(text = bmi.toString())
        }
    }
}


@Composable
private fun GenderSelection(
    bmiViewModel: BmiViewModel, selectedGender: Gender
) {
    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        GenderOptionButton(
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp), text = "Female", onClick = {
                bmiViewModel.selectGender(Gender.Female)
            }, isSelected = selectedGender == Gender.Female
        )
        GenderOptionButton(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp), text = "Male", onClick = {
                bmiViewModel.selectGender(Gender.Male)
            }, isSelected = selectedGender == Gender.Male
        )
    }
}
