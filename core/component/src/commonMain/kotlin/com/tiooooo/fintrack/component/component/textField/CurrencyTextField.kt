package com.tiooooo.fintrack.component.component.textField

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import com.tiooooo.fintrack.component.theme.SMALL_PADDING
import com.tiooooo.fintrack.component.theme.customTextFieldColors
import com.tiooooo.fintrack.component.theme.textMedium14
import com.tiooooo.fintrack.component.theme.textMedium16
import com.tiooooo.fintrack.data.utils.formatRupiah

@Composable
fun CurrencyTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeHolderText: String? = null,
    labelText: String? = null,
) {

    val formattedValue = remember(value) { formatRupiah(value.toDoubleOrNull() ?: 0.0) }
    val textFieldValue = remember(formattedValue) {
        TextFieldValue(
            text = formattedValue,
            selection = TextRange(formattedValue.length)
        )
    }

    Column(modifier = modifier) {
        labelText?.let {
            Text(
                text = it,
                style = textMedium14(),
                modifier = Modifier.padding(bottom = SMALL_PADDING)
            )
        }

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = textFieldValue,
            onValueChange = {
                val numericOnly = it.text.filter { c -> c.isDigit() }
                onValueChange(numericOnly)
            },
            textStyle = textMedium16().copy(color = MaterialTheme.colorScheme.onSurface),
            placeholder = {
                placeHolderText?.let {
                    Text(
                        text = it,
                        style = textMedium16().copy(color = MaterialTheme.colorScheme.onSurfaceVariant),
                        maxLines = 1,
                    )
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            singleLine = true,
            visualTransformation = VisualTransformation.None,
            colors = customTextFieldColors,
            trailingIcon = {
                if (value.isNotEmpty()) {
                    IconButton(onClick = { onValueChange("") }) {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            },
            shape = RoundedCornerShape(SMALL_PADDING),
        )
    }
}

