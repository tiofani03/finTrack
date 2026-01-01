package com.tiooooo.fintrack.feature.settings.pages.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tiooooo.fintrack.component.base.BaseScaffold
import com.tiooooo.fintrack.component.component.sheet.RadioButtonBottomSheet
import com.tiooooo.fintrack.component.component.topBar.BasicTopBarTitle
import com.tiooooo.fintrack.component.theme.MEDIUM_PADDING
import com.tiooooo.fintrack.feature.settings.pages.settings.component.SettingGroupSection
import com.tiooooo.fintrack.feature.settings.pages.settings.component.SettingProfileHeader
import com.tiooooo.fintrack.feature.settings.pages.settings.helper.getCurrencyList
import com.tiooooo.fintrack.feature.settings.pages.settings.helper.getLanguageList
import com.tiooooo.fintrack.feature.settings.pages.settings.helper.getPreferencesItem
import com.tiooooo.fintrack.feature.settings.pages.settings.helper.getTermAndCondition

@Composable
fun SettingScreen(
    modifier: Modifier = Modifier,
    settingScreenModel: SettingScreenModel,
) {
    val state by settingScreenModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(settingScreenModel) {
        settingScreenModel.effect.collect { effect ->
            when (effect) {
                is SettingEffect.NavigateToLogin -> {

                }
            }
        }
    }

    BaseScaffold(
        modifier = modifier,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding()),
        ) {
            BasicTopBarTitle(
                modifier = Modifier
                    .wrapContentSize(),
                title = state.settingTitle,
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                state = state.lazyListState
            ) {
                item {
                    SettingProfileHeader(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = MEDIUM_PADDING)
                    )
                }
                item {
                    SettingGroupSection(
                        modifier = Modifier.padding(top = MEDIUM_PADDING),
                        title = "Preferences",
                        items = getPreferencesItem(
                            state = state,
                            actions = SettingActions(
                                onShowCurrencySheet = {
                                    settingScreenModel.dispatch(
                                        SettingIntent.ShowCurrencySheet(it)
                                    )
                                },
                                onShowLanguageSheet = {
                                    settingScreenModel.dispatch(
                                        SettingIntent.ShowLanguageSheet(it)
                                    )
                                },
                                onThemeChanged = {
                                    settingScreenModel.dispatch(
                                        SettingIntent.UpdateTheme(it)
                                    )
                                },
                            ),
                        )
                    )
                }
                item {
                    SettingGroupSection(
                        modifier = Modifier.padding(top = MEDIUM_PADDING),
                        title = "Account", items = getTermAndCondition(
                            state = state,
                            actions = SettingActions()
                        )
                    )
                }
                item { Spacer(modifier = Modifier.height(80.dp)) }
            }
        }
    }
    RadioButtonBottomSheet(
        visible = state.settingBottomSheetState.isShowLanguageSheetState,
        title = "Select Language",
        items = getLanguageList(),
        selectedId = state.settingItemState.language.id,
        onSelected = {
            settingScreenModel.dispatch(
                SettingIntent.UpdateLanguage(it)
            )
        },
        onDismiss = {
            settingScreenModel.dispatch(
                SettingIntent.ShowLanguageSheet(false)
            )
        }
    )
    RadioButtonBottomSheet(
        visible = state.settingBottomSheetState.isShowCurrencySheetState,
        title = "Select Currency",
        items = getCurrencyList(),
        selectedId = state.settingItemState.currency.id,
        onSelected = {
            settingScreenModel.dispatch(
                SettingIntent.UpdateCurrency(it)
            )
        },
        onDismiss = {
            settingScreenModel.dispatch(
                SettingIntent.ShowCurrencySheet(false)
            )
        }
    )
}
