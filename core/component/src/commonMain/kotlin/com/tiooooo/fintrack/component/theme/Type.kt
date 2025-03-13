package com.tiooooo.fintrack.component.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import fintrack.core.component.generated.resources.Res
import fintrack.core.component.generated.resources.jakarta_sans_bold
import fintrack.core.component.generated.resources.jakarta_sans_medium
import fintrack.core.component.generated.resources.jakarta_sans_regular
import fintrack.core.component.generated.resources.jakarta_sans_semi_bold
import org.jetbrains.compose.resources.Font


@Composable
fun JakartaSansFamily() = FontFamily(
    Font(Res.font.jakarta_sans_regular, weight = FontWeight.Normal),
    Font(Res.font.jakarta_sans_medium, weight = FontWeight.Medium),
    Font(Res.font.jakarta_sans_semi_bold, weight = FontWeight.SemiBold),
    Font(Res.font.jakarta_sans_bold, weight = FontWeight.Bold),
)

@Composable
fun JakartaSansTypography() = Typography().run {

    val fontFamily = JakartaSansFamily()
    copy(
        displayLarge = displayLarge.copy(fontFamily = fontFamily),
        displayMedium = displayMedium.copy(fontFamily = fontFamily),
        displaySmall = displaySmall.copy(fontFamily = fontFamily),
        headlineLarge = headlineLarge.copy(fontFamily = fontFamily),
        headlineMedium = headlineMedium.copy(fontFamily = fontFamily),
        headlineSmall = headlineSmall.copy(fontFamily = fontFamily),
        titleLarge = titleLarge.copy(fontFamily = fontFamily),
        titleMedium = titleMedium.copy(fontFamily = fontFamily),
        titleSmall = titleSmall.copy(fontFamily = fontFamily),
        bodyLarge = bodyLarge.copy(fontFamily = fontFamily),
        bodyMedium = bodyMedium.copy(fontFamily = fontFamily),
        bodySmall = bodySmall.copy(fontFamily = fontFamily),
        labelLarge = labelLarge.copy(fontFamily = fontFamily),
        labelMedium = labelMedium.copy(fontFamily = fontFamily),
        labelSmall = labelSmall.copy(fontFamily = fontFamily)
    )
}