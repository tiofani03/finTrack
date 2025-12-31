package com.tiooooo.fintrack.feature.settings.pages.settings.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import com.tiooooo.fintrack.component.theme.AppTheme
import com.tiooooo.fintrack.component.theme.EXTRA_SMALL_PADDING
import com.tiooooo.fintrack.component.theme.MEDIUM_PADDING

@Composable
fun ChooseThemeDialog(
    currentTheme: AppTheme,
    onDismiss: () -> Unit,
    onConfirm: (AppTheme) -> Unit
) {
    var selectedTheme by remember { mutableStateOf(currentTheme) }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = { onConfirm(selectedTheme) }) {
                Text("Apply")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        },
        title = { Text("Select Theme") },
        text = {
            Column {
                AppTheme.entries.forEach { theme ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = selectedTheme == theme,
                                onClick = { selectedTheme = theme },
                                role = Role.RadioButton
                            )
                            .padding(vertical = EXTRA_SMALL_PADDING)
                    ) {
                        RadioButton(
                            selected = selectedTheme == theme,
                            onClick = null
                        )
                        Spacer(modifier = Modifier.width(MEDIUM_PADDING))
                        Text(text = theme.label)
                    }
                }
            }
        }
    )
}