package com.example.calcx.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.calcx.ui.screens.age.AgeCalculatorScreen
import com.example.calcx.ui.screens.age.AgeResultScreen
import com.example.calcx.ui.screens.bmi.BmiCalculationScreen
import com.example.calcx.ui.screens.bmi.BmiResultScreen
import com.example.calcx.ui.screens.discount.DiscountCalculatorScreen
import com.example.calcx.ui.screens.landing.LandingScreen
import com.example.calcx.viewmodel.AgeViewModel
import com.example.calcx.viewmodel.BmiViewModel
import com.example.calcx.viewmodel.DiscountViewModel

object Routes {
    const val LANDING = "landing"
    const val AGE = "age"
    const val AGE_RESULT = "ageResult"
    const val BMI = "bmi"
    const val BMI_RESULT = "bmiResult"
    const val DISCOUNT_CALCULATOR = "discountCalculator"
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavHost(navController: NavHostController) {
    val ageViewModel: AgeViewModel = viewModel()
    val bmiViewModel: BmiViewModel = viewModel()
    val discountViewModel: DiscountViewModel = viewModel()
    NavHost(navController = navController, startDestination = Routes.LANDING) {
        composable(Routes.LANDING) { LandingScreen(navController) }
        composable(Routes.AGE) { AgeCalculatorScreen(navController, ageViewModel) }
        composable(Routes.AGE_RESULT) { AgeResultScreen(navController, ageViewModel) }
        composable(Routes.BMI) { BmiCalculationScreen(navController, bmiViewModel) }
        composable(Routes.BMI_RESULT) { BmiResultScreen(navController, bmiViewModel) }
        composable(Routes.DISCOUNT_CALCULATOR) { DiscountCalculatorScreen(discountViewModel) }

    }
}
