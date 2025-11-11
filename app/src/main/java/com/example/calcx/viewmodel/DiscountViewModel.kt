package com.example.calcx.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class DiscountViewModel : ViewModel() {
    var orginalPrice by mutableStateOf("")
    var discountPercentage by mutableStateOf("")
    var discountAmount by mutableStateOf(0.0)
    var finalPrice by mutableStateOf(0.0)
    fun CalculateDiscount() {
        var orginalPriceValue: Double = orginalPrice.toDoubleOrNull() ?: 0.0
        var discountPercentageValue: Double = discountPercentage.toDoubleOrNull() ?: 0.0
        discountAmount = orginalPriceValue * (discountPercentageValue / 100)
        finalPrice = (orginalPriceValue - discountAmount)
    }
}