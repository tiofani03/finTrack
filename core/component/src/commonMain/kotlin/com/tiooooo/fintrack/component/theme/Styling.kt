package com.tiooooo.fintrack.component.theme

import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val customTextFieldColors
    @Composable get() = TextFieldDefaults.colors(
        focusedTextColor = MaterialTheme.colorScheme.onSurface,
        unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
        disabledTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
        errorTextColor = MaterialTheme.colorScheme.onError,

        focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
        disabledContainerColor = MaterialTheme.colorScheme.surfaceContainer.copy(alpha = 0.3f),
        errorContainerColor = MaterialTheme.colorScheme.errorContainer,

        cursorColor = MaterialTheme.colorScheme.primary,
        errorCursorColor = MaterialTheme.colorScheme.error,

        selectionColors = TextSelectionColors(
            handleColor = MaterialTheme.colorScheme.primary,
            backgroundColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
        ),

        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        errorIndicatorColor = Color.Transparent,

        focusedLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        unfocusedLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f),
        errorLeadingIconColor = MaterialTheme.colorScheme.error,

        focusedTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        unfocusedTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f),
        errorTrailingIconColor = MaterialTheme.colorScheme.error,

        focusedLabelColor = MaterialTheme.colorScheme.primary,
        unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f),
        errorLabelColor = MaterialTheme.colorScheme.error,

        focusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
        unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f),
        errorPlaceholderColor = MaterialTheme.colorScheme.error,

        focusedSupportingTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
        unfocusedSupportingTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledSupportingTextColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f),
        errorSupportingTextColor = MaterialTheme.colorScheme.error,

        focusedPrefixColor = MaterialTheme.colorScheme.onSurfaceVariant,
        unfocusedPrefixColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledPrefixColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f),
        errorPrefixColor = MaterialTheme.colorScheme.error,

        focusedSuffixColor = MaterialTheme.colorScheme.onSurfaceVariant,
        unfocusedSuffixColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledSuffixColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f),
        errorSuffixColor = MaterialTheme.colorScheme.error,
    )


val customTextFieldDefaultColors
    @Composable get() = TextFieldDefaults.colors(
        // Text Color
        focusedTextColor = MaterialTheme.colorScheme.onSurface,
        unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
        disabledTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
        errorTextColor = MaterialTheme.colorScheme.onError,

        // Background Colors
        focusedContainerColor = Color.Transparent, // Transparent when focused
        unfocusedContainerColor = Color.Transparent, // Transparent when unfocused
        disabledContainerColor = Color.Transparent, // Transparent when disabled
        errorContainerColor = MaterialTheme.colorScheme.errorContainer,

        // Cursor Colors
        cursorColor = MaterialTheme.colorScheme.primary,
        errorCursorColor = MaterialTheme.colorScheme.error,

        // Text Selection Colors
        selectionColors = TextSelectionColors(
            handleColor = MaterialTheme.colorScheme.primary,
            backgroundColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
        ),

        // Indicator Colors
        focusedIndicatorColor = Color.Transparent, // Transparent indicator when focused
        unfocusedIndicatorColor = Color.Transparent, // Transparent indicator when unfocused
        disabledIndicatorColor = Color.Transparent, // Transparent indicator when disabled
        errorIndicatorColor = Color.Transparent, // Transparent indicator on error

        // Icon Colors
        focusedLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        unfocusedLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f),
        errorLeadingIconColor = MaterialTheme.colorScheme.error,

        focusedTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        unfocusedTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f),
        errorTrailingIconColor = MaterialTheme.colorScheme.error,

        // Label Colors
        focusedLabelColor = MaterialTheme.colorScheme.primary,
        unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f),
        errorLabelColor = MaterialTheme.colorScheme.error,

        // Placeholder Colors
        focusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
        unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f),
        errorPlaceholderColor = MaterialTheme.colorScheme.error,

        // Supporting Text Colors
        focusedSupportingTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
        unfocusedSupportingTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledSupportingTextColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f),
        errorSupportingTextColor = MaterialTheme.colorScheme.error,

        // Prefix/Suffix Text Colors
        focusedPrefixColor = MaterialTheme.colorScheme.onSurfaceVariant,
        unfocusedPrefixColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledPrefixColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f),
        errorPrefixColor = MaterialTheme.colorScheme.error,

        focusedSuffixColor = MaterialTheme.colorScheme.onSurfaceVariant,
        unfocusedSuffixColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledSuffixColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f),
        errorSuffixColor = MaterialTheme.colorScheme.error
    )


const val ALPHA_COLOR = 0.8f

data class AppColors(
    val textWhiteColor: Color,
    val backgroundPrimaryLightColor: Color,
    val backgroundPrimaryDarkColor: Color,
)

val finTrackLight = AppColors(
    textWhiteColor = Color.White,
    backgroundPrimaryLightColor = primaryLight,
    backgroundPrimaryDarkColor = primaryDark
)

val finTrackDark = AppColors(
    textWhiteColor = Color.White.copy(ALPHA_COLOR),
    backgroundPrimaryLightColor = primaryLight.copy(ALPHA_COLOR),
    backgroundPrimaryDarkColor = primaryDark.copy(ALPHA_COLOR)
)

val FinTrackAppColors = staticCompositionLocalOf<AppColors> {
    error("No AppColors provided")
}
