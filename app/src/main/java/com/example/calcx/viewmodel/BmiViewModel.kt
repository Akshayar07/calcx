package com.example.calcx.viewmodel

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.calcx.model.Gender
import kotlin.math.pow

class BmiViewModel : ViewModel() {
    //---states---
    var selectedGender by mutableStateOf(Gender.Female)
    var weight by mutableStateOf("")
    var height by mutableStateOf("")
    var age by mutableStateOf("")
    var bmi by mutableDoubleStateOf(0.0)
    var category by mutableStateOf("")
    var healthTip by mutableStateOf("")
    var weightError by mutableStateOf<String?>(null)
    var heightError by mutableStateOf<String?>(null)
    var ageError by mutableStateOf<String?>(null)


    //---isValidNumericValues---
    fun isValidNumericValues(): Boolean {
        var heightValue: Double = height.toDoubleOrNull() ?: 0.0
        var weightValue: Double = weight.toDoubleOrNull() ?: 0.0
        var ageValue: Int = age.toIntOrNull() ?: 0
        return heightValue > 0 && weightValue > 0 && ageValue > 0

    }

    //---Validation---
    fun validateField(): Boolean {
        weightError = if (weight.isBlank()) {
            "Please enter a valid weight"
        } else {
            null
        }
        heightError = if (height.isBlank()) {
            "Please enter a valid height"
        } else {
            null
        }
        ageError = if (age.isBlank()) {
            "Please enter a valid age"
        } else {
            null
        }
        return listOf(weightError, heightError, ageError).all { it == null }
    }

    //---Gender Selection---
    fun selectGender(gender: Gender) {
        selectedGender = gender
    }

    //---Bmi Calculation---
    @SuppressLint("DefaultLocale")
    fun calculateBmi() {
        var heightValue: Double = height.toDoubleOrNull() ?: 0.0
        var weightValue: Double = weight.toDoubleOrNull() ?: 0.0
        if (heightValue <= 0 || weightValue <= 0) {
            bmi = 0.0
            category = ""
            healthTip = ""
            return
        }
        var heightInMeters: Double = heightValue / 100
        val calculatedBmi: Double = weightValue / heightInMeters.pow(2)
        bmi = String.format("%.2f", calculatedBmi).toDouble()

        //---Category Classification---
        category = getCategory(bmi)
        //---Health Tip---
        healthTip = getHealthTip(category)

    }

    fun reset() {
        selectedGender = Gender.Male
        weight = ""
        height = ""
        age = ""
        bmi = 0.0
        category = ""
        healthTip = ""
    }

    // ---Health Tip---
    private fun getHealthTip(category: String): String {
        return when (category) {
            "Underweight" -> "Consider consulting a nutritionist for a healthy weight gain plan."
            "Normal" -> "Great job! Maintain your healthy lifestyle with regular exercise."
            "Overweight" -> "A balanced diet and regular exercise can help you reach your goals."
            "Obesity" -> "Consult a healthcare professional for personalized guidance."
            else -> ""
        }

    }

// --- Category Classification ---

    private fun getCategory(bmi: Double): String {
        return if (selectedGender == Gender.Male) {
            when {
                bmi < 18.5 -> "Underweight"
                bmi < 25 -> "Normal"
                bmi < 30 -> "Overweight"
                else -> "Obesity"
            }

        } else {
            when {
                bmi < 16 -> "Underweight"
                bmi < 23 -> "Normal"
                bmi < 27 -> "Overweight"
                else -> "Obesity"
            }
        }

    }
}

