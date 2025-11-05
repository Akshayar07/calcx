package com.example.calcx.ui.theme.screens.bmi

import AppButton
import android.widget.Toast
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.calcx.model.Gender
import com.example.calcx.ui.theme.components.GenderOptionButton
import com.example.calcx.ui.theme.components.LabeledInputField
import com.example.calcx.ui.theme.components.SectionTitle
import com.example.calcx.ui.theme.navigation.Routes
import com.example.calcx.viewmodel.BmiViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BmiCalculationScreen(
    navController: NavHostController,
    bmiViewModel: BmiViewModel = viewModel(),
) {

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    Scaffold(
        topBar = {
            TopAppBar(

                title = { Text("BMI Calculator") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(Routes.LANDING) }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = {
                            focusManager.clearFocus() // 👈 hides the keyboard
                        }
                    )
                }
                .padding(16.dp)
        ) {

            Text(text = "Track your body mass index")
            SectionTitle(
                title = "Gender",
                modifier = Modifier.padding(top = 24.dp, bottom = 8.dp),
            )
            GenderSelection(
                bmiViewModel = bmiViewModel,
                selectedGender = bmiViewModel.selectedGender
            )
            SectionTitle(
                title = "Weight (kg)",
                modifier = Modifier.padding(top = 24.dp, bottom = 8.dp),
            )
            LabeledInputField(
                errorMessage = bmiViewModel.weightError,
                value = bmiViewModel.weight.toString(),
                label = "Enter Your Weight",
                onValueChange = { bmiViewModel.weight = it })
            SectionTitle(
                title = "Height (cm)",
                modifier = Modifier.padding(top = 24.dp, bottom = 8.dp),
            )
            LabeledInputField(
                value = bmiViewModel.height,
                label = "Enter Your Height",
                onValueChange = { bmiViewModel.height = it },
                errorMessage = bmiViewModel.heightError
            )
            SectionTitle(
                title = "Age",
                modifier = Modifier.padding(top = 24.dp, bottom = 8.dp),

                )
            LabeledInputField(

                value = bmiViewModel.age,
                label = "Enter Your Age",
                onValueChange = { bmiViewModel.age = it },
                errorMessage = bmiViewModel.ageError
            )
            Spacer(modifier = Modifier.height(24.dp))
            AppButton(
                label = "Calculate BMI",
                onClick = {
                    if (!bmiViewModel.validateField()) return@AppButton
                    if (!bmiViewModel.isValidNumericValues()) {
                        Toast.makeText(context, "Values must be greater than 0", Toast.LENGTH_SHORT)
                            .show()
                        return@AppButton
                    }
                    navController.navigate(Routes.BMI_RESULT)
                    bmiViewModel.calculateBmi()
                })
        }
    }
}

fun validate(weight: String, height: String, age: String): String {
    if (weight.isBlank() || height.isBlank() || age.isBlank()) {
        return "Please fill all the fields"
    }
    val weightValue = weight.toDouble()
    val heightValue = height.toDouble()
    val ageValue = age.toInt()
    if (weightValue == null || weightValue <= 0) return "Please enter valid weight"
    if (heightValue == null || heightValue <= 0) return "Please enter valid height"
    if (ageValue == null || ageValue <= 0) return "Please enter valid age"
    return ""

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
