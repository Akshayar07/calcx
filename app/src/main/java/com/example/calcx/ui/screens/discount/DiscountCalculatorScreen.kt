package com.example.calcx.ui.screens.discount

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CurrencyRupee
import androidx.compose.material.icons.filled.Discount
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.calcx.ui.components.LabeledInputField
import com.example.calcx.ui.components.SectionTitle
import com.example.calcx.viewmodel.DiscountViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscountCalculatorScreen(discountViewModel: DiscountViewModel) {
    println("Original Price: ${discountViewModel.orginalPrice}")
    println("Discount Percentage: ${discountViewModel.discountPercentage}")
    val originalPrice = discountViewModel.orginalPrice.toDoubleOrNull()
    val discountPercentage = discountViewModel.discountPercentage.toDoubleOrNull()
    val focusManager = LocalFocusManager.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Discount Calculator")
                },
            )
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .verticalScroll(
                    state = rememberScrollState()
                )
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = {
                            focusManager.clearFocus()
                        })
                }) {
            SectionTitle(
                title = "Original Price",
                modifier = Modifier.padding(top = 24.dp, bottom = 8.dp),
            )
            LabeledInputField(
                value = discountViewModel.orginalPrice, onValueChange = {
                    discountViewModel.orginalPrice = it
                    discountViewModel.CalculateDiscount()

                    555588
                }, leadingIcon = Icons.Default.CurrencyRupee, placeholder = "0.00"
            )
            SectionTitle(
                title = "Discount",
                modifier = Modifier.padding(top = 24.dp, bottom = 8.dp),
            )
            LabeledInputField(
                value = discountViewModel.discountPercentage, onValueChange = {
                    discountViewModel.discountPercentage = it
                    discountViewModel.CalculateDiscount()
                }, trailingIcon = Icons.Default.Discount, placeholder = "0"
            )
            SectionTitle(
                title = "Quick Select",
                modifier = Modifier.padding(top = 24.dp, bottom = 8.dp),
            )
            Row {

            }
            if (!discountViewModel.orginalPrice.isNullOrBlank() &&
                !discountViewModel.discountPercentage.isNullOrBlank() &&
                originalPrice != null && originalPrice >= 0 &&
                discountPercentage != null && discountPercentage >= 0
            ) {
                DiscountResult(discountViewModel)

            } else {
                EmptyPlaceholder()
            }
        }
    }

}

@Composable
private fun DiscountResult(discountViewModel: DiscountViewModel) {
    Column(
        modifier = Modifier.fillMaxWidth()

    ) {
        PriceCard(
            title = "You Save",
            price = "₹ ${discountViewModel.discountAmount}",
            backgroundColor = Color.Green
        )
        PriceCard(
            title = "Final Price",
            price = "₹ ${discountViewModel.finalPrice}",
            backgroundColor = Color.Blue
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Original")
            Text(
                text = "₹ ${discountViewModel.orginalPrice}",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Discount (${discountViewModel.discountPercentage}%)")
            Text(
                text = "- ₹ ${discountViewModel.discountAmount}",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 8.dp),
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "You Pay", style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = "₹ ${discountViewModel.finalPrice}",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

@Composable
private fun EmptyPlaceholder() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()
    ) {
        Card(
            shape = CircleShape,
        ) {
            Icon(
                imageVector = Icons.Default.Discount,
                contentDescription = "Discount",
                modifier = Modifier
                    .size(70.dp)
                    .padding(16.dp)
            )
        }

        SectionTitle(
            title = "Enter price and discount to calculate",
            modifier = Modifier.padding(top = 24.dp, bottom = 8.dp),
        )
    }
}

@Composable
private fun PriceCard(title: String, price: String, backgroundColor: Color) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            SectionTitle(
                title = title,
                color = Color.White,
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
            )
            Text(
                text = price,
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White,
                modifier = Modifier.padding(top = 8.dp)
            )

        }
    }
}