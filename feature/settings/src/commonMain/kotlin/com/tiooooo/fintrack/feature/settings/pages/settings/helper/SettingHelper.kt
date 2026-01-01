package com.tiooooo.fintrack.feature.settings.pages.settings.helper

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Web
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.tiooooo.fintrack.component.component.sheet.RadioItem
import com.tiooooo.fintrack.component.theme.AppTheme
import com.tiooooo.fintrack.component.theme.buttonPrimaryColor
import com.tiooooo.fintrack.component.theme.textMedium10
import com.tiooooo.fintrack.feature.settings.pages.settings.SettingActions
import com.tiooooo.fintrack.feature.settings.pages.settings.SettingState
import com.tiooooo.fintrack.feature.settings.pages.settings.component.SettingItem

fun getPreferencesItem(
    state: SettingState,
    actions: SettingActions,
) = listOf(
    SettingItem(
        title = "Currency",
        icon = Icons.Filled.Money,
        iconTint = buttonPrimaryColor,
        actionContent = {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = state.settingItemState.currency.label,
                    style = textMedium10().copy(
                        fontWeight = FontWeight.Light
                    ),
                )
                Icon(imageVector = Icons.Default.ChevronRight, contentDescription = "Next")
            }
        },
        onClick = {
            actions.onShowCurrencySheet(true)
        }
    ),
    SettingItem(
        title = "Language",
        icon = Icons.Filled.Web,
        iconTint = Color.Magenta,
        actionContent = {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = state.settingItemState.language.label,
                    style = textMedium10().copy(
                        fontWeight = FontWeight.Light
                    ),
                )
                Icon(imageVector = Icons.Default.ChevronRight, contentDescription = "Next")
            }
        },
        onClick = {
            actions.onShowLanguageSheet(true)
        }
    ),
    SettingItem(
        title = "Dark mode",
        icon = Icons.Filled.DarkMode,
        iconTint = Color.Yellow,
        actionContent = {
            Switch(
                checked = state.settingItemState.isDarkModeActive,
                onCheckedChange = {
                    actions.onThemeChanged(
                        if (it) {
                            AppTheme.DARK.label
                        } else {
                            AppTheme.LIGHT.label
                        }
                    )
                },
                modifier = Modifier
                    .scale(0.8f)
                    .minimumInteractiveComponentSize()
            )
        },
        onClick = {
            actions.onThemeChanged(
                if (state.settingItemState.isDarkModeActive) {
                    AppTheme.LIGHT.label
                } else {
                    AppTheme.DARK.label
                }
            )
        }
    ),
)

fun getTermAndCondition(state: SettingState, actions: SettingActions) = listOf(
    SettingItem("Help & Support", Icons.Filled.Info),
    SettingItem(
        title = "Logout",
        icon = Icons.AutoMirrored.Filled.ExitToApp,
        actionContent = {

        },
        textColor = Color.Red,
        iconTint = Color.Red,
    ),
)

fun getLanguageList(): List<RadioItem<String>> {
    return listOf(
        RadioItem("en", "English"),
        RadioItem("id", "Bahasa Indonesia"),
    )
}

fun getCurrencyList(): List<RadioItem<String>> {
    return listOf(
        RadioItem("IDR", "IDR - Indonesian Rupiah"),
        RadioItem("USD", "USD - United States Dollar"),
        RadioItem("EUR", "EUR - Euro"),
    )
}