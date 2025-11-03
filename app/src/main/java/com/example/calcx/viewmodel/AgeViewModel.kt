package com.example.calcx.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class AgeViewModel : ViewModel() {
    var years: Int by mutableIntStateOf(0)
    var months: Int by mutableIntStateOf(0)
    var days: Int by mutableIntStateOf(0)
}