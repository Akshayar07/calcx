package com.example.calcx.ui.theme.screens.age

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.calcx.ui.theme.navigation.Routes
import com.example.calcx.utils.DateUtils
import com.example.calcx.viewmodel.AgeViewModel
import java.time.Instant
import java.time.LocalDate
import java.time.Period
import java.time.ZoneId
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgeCalculatorScreen(navController: NavHostController, ageViewModel: AgeViewModel) {

    var selectedDate: String? by remember { mutableStateOf<String?>(null) }

    var showDatePicker: Boolean by remember { mutableStateOf(false) }
    val today = LocalDate.now()

    var datePickerState: DatePickerState = rememberDatePickerState(
        initialSelectedDateMillis = today.toEpochDay() * 24 * 60 * 60 * 1000,

        )
//    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {

                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                title = { Text(text = "Age Calculator") })
        }
    )
    { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(innerPadding)
                .padding(horizontal = 40.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Let's Find Out Your Real Age",
                style = TextStyle(
                    fontSize = 35.sp,
                    fontWeight = FontWeight.W800,
                    textAlign = TextAlign.Center
                )
            )
            Spacer(modifier = Modifier.height(40.dp))
            Card {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 40.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "DATE OF BIRTH",
                        style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.W500)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    ExtendedFloatingActionButton(
                        containerColor = Color.DarkGray,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        elevation = FloatingActionButtonDefaults.elevation(
                            defaultElevation = 8.dp,
                            focusedElevation = 10.dp
                        ),
                        onClick = {
                            showDatePicker = true
                        },
                        text = {
                            Text(text = selectedDate ?: "DD-MM-YYYY")
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.DateRange,
                                contentDescription = "Calender icon"
                            )
                        }
                    )

                }
            }
            Spacer(modifier = Modifier.height(40.dp))
            if (selectedDate != null) {


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.Bottom,

                    ) {
                    Button(
                        onClick = {
                            val birthDate = LocalDate.parse(selectedDate, DateUtils.formatter)
                            ageViewModel.years = Period.between(birthDate, LocalDate.now()).years
                            ageViewModel.months = Period.between(birthDate, LocalDate.now()).months
                            ageViewModel.days = Period.between(birthDate, LocalDate.now()).days
                            navController.navigate(Routes.AGE_RESULT)
                        }
                    ) {
                        Row {
                            Text(text = "Ok")
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = "Result"
                            )

                        }
                    }
                }

            }
        }
    }

    if (showDatePicker)
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    val selectedMillis = datePickerState.selectedDateMillis ?: today.atStartOfDay(
                        ZoneId.systemDefault()
                    ).toInstant()
                        .toEpochMilli()

                    selectedMillis.let {
                        val date =
                            Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault()).toLocalDate()
                        val formattedDate = date.format(DateUtils.formatter)

                        selectedDate = formattedDate
                    }
                    showDatePicker = false
                }) {
                    Text(text = "Ok")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text(text = "cancel")
                }
            }

        ) {
            DatePicker(
                state = datePickerState
            )
        }
}

