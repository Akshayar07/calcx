package com.example.calcx.viewmodel

import android.annotation.SuppressLint
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.calcx.model.Gender

class BmiViewModel : ViewModel() {
    var selectedGender: MutableState<Gender> = mutableStateOf(Gender.Male)
    var weight: MutableState<String> = mutableStateOf("")
    var height: MutableState<String> = mutableStateOf("")
    var age: MutableState<String> = mutableStateOf("")
    var bmi: MutableState<Double> = mutableDoubleStateOf(0.0)
    var category: MutableState<String> = mutableStateOf("")
    var healthTip: MutableState<String> = mutableStateOf("")
    fun selectGender(gender: Gender) {
        selectedGender.value = gender
    }

    @SuppressLint("DefaultLocale")
    fun calculateBmi() {
        var heightValue: Double = height.value.toDoubleOrNull() ?: 0.0
        var weightValue: Double = weight.value.toDoubleOrNull() ?: 0.0
        var heightInMeters: Double = heightValue / 100
        val calculatedBmi: Double = (weightValue / (heightInMeters * heightInMeters))
        bmi.value = String.format("%.2f", calculatedBmi).toDouble()
        if (selectedGender.value == Gender.Male) {
            if (calculatedBmi < 18.5) {
                category.value = "Underweight"
            } else if (calculatedBmi < 25) {
                category.value = "Normal"
            } else if (calculatedBmi < 30) {
                category.value = "Overweight"
            } else {
                category.value = "Obesity"
            }
        } else {
            if (calculatedBmi < 16) {
                category.value = "Underweight"
            } else if (calculatedBmi < 23) {
                category.value = "Normal"
            } else if (calculatedBmi < 27) {
                category.value = "Overweight"
            } else {
                category.value = "Obesity"
            }
        }
        getHealthTip(category.value)

    }

    private fun getHealthTip(string: String) {
        if (string == "Underweight") {
            healthTip.value = "Consider consulting a nutritionist for a healthy weight gain plan."
        } else if (string == "Normal") {
            healthTip.value = "Great job! Maintain your healthy lifestyle with regular exercise."
        } else if (string == "Overweight") {
            healthTip.value = "A balanced diet and regular exercise can help you reach your goals."
        } else {
            healthTip.value = "Consult a healthcare professional for personalized guidance."
        }
    }


}