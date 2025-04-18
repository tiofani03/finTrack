package com.tiooooo.fintrack.component.component.textField

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tiooooo.fintrack.component.theme.EXTRA_SMALL_PADDING
import com.tiooooo.fintrack.component.theme.SMALL_PADDING
import com.tiooooo.fintrack.component.theme.customTextFieldColors
import com.tiooooo.fintrack.component.theme.textMedium12
import com.tiooooo.fintrack.component.theme.textMedium14

@Composable
fun StringTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeHolderText: String? = null,
    labelText: String? = null,
    maxChar: Int? = null,
) {
    Column(
        modifier = modifier,
    ) {
        labelText?.let {
            Text(
                text = it,
                style = textMedium14(),
                modifier = Modifier.padding(bottom = SMALL_PADDING)
            )
        }

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            textStyle = textMedium14().copy(color = MaterialTheme.colorScheme.onSurface),
            onValueChange = {
                maxChar?.let { maxChar ->
                    if (it.length <= maxChar) onValueChange(it)
                } ?: kotlin.run {
                    onValueChange(it)
                }
            },
            shape = RoundedCornerShape(SMALL_PADDING),
            placeholder = {
                placeHolderText?.let {
                    Text(
                        text = it,
                        style = textMedium14().copy(color = MaterialTheme.colorScheme.onSurfaceVariant),
                        maxLines = 1,
                    )
                }
            },
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
        )

        if (maxChar != null && value.isNotEmpty()) {
            Text(
                text = "${value.length} / $maxChar",
                style = textMedium12().copy(color = MaterialTheme.colorScheme.onSurfaceVariant),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = EXTRA_SMALL_PADDING)
                    .align(Alignment.End),
                textAlign = TextAlign.End
            )
        }
    }
}



