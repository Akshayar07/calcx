package com.example.calcx.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun LabeledInputField(
    value: String,
    label: String? = null,
    placeholder: String? = null,
    onValueChange: (String) -> Unit,
    errorMessage: String? = null,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            isError = errorMessage != null,
            leadingIcon = if (leadingIcon != null) {
                {
                    Icon(
                        imageVector = leadingIcon, contentDescription = "Kilogram"
                    )
                }
            } else null,
            trailingIcon = if (trailingIcon != null) {
                {
                    Icon(imageVector = trailingIcon, contentDescription = "Clear")
                }
            } else null,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            placeholder = if (placeholder != null) {
                {
                    Text(text = placeholder)
                }
            } else null,
            label = if (label != null) {
                {
                    Text(text = label)
                }
            } else null

        )
        // show error message if present
        if (errorMessage != null) {
            Text(
                text = errorMessage,
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }

    }

}
