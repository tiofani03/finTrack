package com.tiooooo.fintrack.feature.settings.pages.settings

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Web
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.tiooooo.fintrack.component.base.BaseScaffold
import com.tiooooo.fintrack.component.component.topBar.BasicTopBarTitle
import com.tiooooo.fintrack.component.theme.AppTheme
import com.tiooooo.fintrack.component.theme.ICON_CARD_SIZE
import com.tiooooo.fintrack.component.theme.MEDIUM_PADDING
import com.tiooooo.fintrack.component.theme.SMALL_PADDING
import com.tiooooo.fintrack.component.theme.buttonPrimaryColor
import com.tiooooo.fintrack.component.theme.textMedium10
import com.tiooooo.fintrack.component.theme.textMedium14
import com.tiooooo.fintrack.component.theme.textMedium16
import com.tiooooo.fintrack.feature.settings.pages.settings.component.ChooseThemeDialog

@Composable
fun SettingScreen(
    modifier: Modifier = Modifier,
    settingScreenModel: SettingScreenModel,
) {
    val listState by settingScreenModel.lazyListState.collectAsState()
    LocalNavigator.currentOrThrow
    val state by settingScreenModel.state.collectAsState()

    LaunchedEffect(Unit) {
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
                state = listState
            ) {
                item {
                    ProfileHeader(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = MEDIUM_PADDING)
                    )
                }
                item {
                    SectionList(
                        modifier = Modifier.padding(top = MEDIUM_PADDING),
                        title = "Preferences",
                        items = getDummyHelpItems()
                    )
                }
                item {
                    SectionList(
                        modifier = Modifier.padding(top = MEDIUM_PADDING),
                        title = "Account", items = getTermAndCondition()
                    )
                }
                item { Spacer(modifier = Modifier.height(80.dp)) }
            }
        }
    }

    if (state.isShowDialogTheme) {
        ChooseThemeDialog(
            currentTheme = AppTheme.fromValue(state.activeTheme),
            onDismiss = { settingScreenModel.dispatch(SettingIntent.ShowDialogTheme(false)) },
            onConfirm = {
                settingScreenModel.dispatch(SettingIntent.ShowDialogTheme(false))
                settingScreenModel.dispatch(SettingIntent.UpdateTheme(it.label))
            }
        )
    }
}


@Composable
fun ProfileHeader(
    modifier: Modifier = Modifier,
) {
    OutlinedCard(
        modifier = modifier,
        shape = RoundedCornerShape(SMALL_PADDING),
        border = BorderStroke(0.dp, MaterialTheme.colorScheme.surface),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MEDIUM_PADDING)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(MEDIUM_PADDING))
                    .padding(MEDIUM_PADDING),
                verticalAlignment = Alignment.CenterVertically
            ) {
//                Image(
//                    painter = painterResource(Res.drawable.ic_login_google),
//                    contentDescription = "Profile Picture",
//                    modifier = Modifier
//                        .size(50.dp)
//                        .clip(CircleShape)
//                )
                Spacer(modifier = Modifier.width(MEDIUM_PADDING))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Tio Fani",
                        style = textMedium16().copy(
                            fontWeight = FontWeight.Bold
                        ),
                    )
                    Text(
                        text = "0821-3713-8344",
                        style = textMedium14().copy(
                            fontWeight = FontWeight.Light,
                        )
                    )
                }

                Text(
                    text = "Ubah",
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.clickable { /* TODO: Handle click */ }
                )
            }
        }
    }
}

@Composable
fun LoyaltyCodeSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MEDIUM_PADDING)
            .clip(RoundedCornerShape(MEDIUM_PADDING))
            .background(Color(0xFFF6F6F6))
            .padding(MEDIUM_PADDING),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Default.QrCodeScanner, contentDescription = "Barcode")
            Spacer(modifier = Modifier.width(SMALL_PADDING))
            Text(text = "Loyalty Code", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
    }
}

@Composable
fun SectionList(
    modifier: Modifier = Modifier,
    title: String,
    items: List<ProfileItem>,
    onItemClicked: (ProfileItem) -> Unit = { },
) {
    Column(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier.padding(start = MEDIUM_PADDING, top = SMALL_PADDING),
            text = title,
            style = textMedium16().copy(
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(SMALL_PADDING))

        OutlinedCard(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = MEDIUM_PADDING)
                .clip(RoundedCornerShape(MEDIUM_PADDING)),
            shape = RoundedCornerShape(MEDIUM_PADDING),
            border = BorderStroke(0.dp, MaterialTheme.colorScheme.surface),
        ) {
            items.forEach { item ->
                ProfileListItem(item, onItemClicked = onItemClicked)
            }
        }
    }
}

@Composable
fun ProfileListItem(
    item: ProfileItem,
    onItemClicked: (ProfileItem) -> Unit = { },
) {
    Row(
        modifier = Modifier
            .clickable {
                onItemClicked.invoke(item)
            }
            .fillMaxWidth()
            .padding(MEDIUM_PADDING),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(ICON_CARD_SIZE)
                .clip(RoundedCornerShape(MEDIUM_PADDING))
                .background(
                    color =
                        item.iconTint?.copy(0.1f)
                            ?: MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                ),

            ) {
            item.icon?.let {
                Icon(
                    modifier = Modifier.padding(SMALL_PADDING),
                    imageVector = item.icon,
                    contentDescription = item.title,
                    tint = item.iconTint ?: MaterialTheme.colorScheme.onSurface,
                )
            }
        }
        Spacer(modifier = Modifier.width(MEDIUM_PADDING))

        Text(
            text = item.title,
            style = textMedium14().copy(
                fontWeight = FontWeight.Light
            ),
            modifier = Modifier.weight(1f),
            color = item.textColor ?: Color.Unspecified
        )

        if (item.actionContent != null) {
            item.actionContent.invoke()
        } else {
            Icon(imageVector = Icons.Default.ChevronRight, contentDescription = "Next")
        }
    }
}


data class ProfileItem(
    val title: String,
    val icon: ImageVector? = null,
    val textColor: Color? = null,
    val intent: SettingIntent? = null,
    val iconTint: Color? = null,
    val actionContent: @Composable (() -> Unit)? = null,
)

//fun getDummyAccountItems() = listOf(
//    ProfileItem("Akun Premium", Icons.Default.Star, "Upgrade"),
//)

fun getDummyHelpItems() = listOf(
    ProfileItem(
        title = "Currency",
        icon = Icons.Filled.Money,
        iconTint = buttonPrimaryColor,
        actionContent = {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "IDR - Indonesian Rupiah",
                    style = textMedium10().copy(
                        fontWeight = FontWeight.Light
                    ),
                )
                Icon(imageVector = Icons.Default.ChevronRight, contentDescription = "Next")
            }
        }
    ),
    ProfileItem(
        title = "Language",
        icon = Icons.Filled.Web,
        iconTint = Color.Magenta,
        actionContent = {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "English",
                    style = textMedium10().copy(
                        fontWeight = FontWeight.Light
                    ),
                )
                Icon(imageVector = Icons.Default.ChevronRight, contentDescription = "Next")
            }
        }
    ),
    ProfileItem(
        title = "Dark mode",
        icon = Icons.Filled.DarkMode,
        iconTint = Color.Yellow,
        actionContent = {
            Switch(
                modifier = Modifier.size(24.dp),
                checked = true,
                onCheckedChange = { /* TODO: Handle change */ }
            )
        }
    ),
)

fun getTermAndCondition() = listOf(
    ProfileItem("Help & Support", Icons.Filled.Info),
    ProfileItem(
        title = "Logout",
        icon = Icons.AutoMirrored.Filled.ExitToApp,
        actionContent = {

        },
        textColor = Color.Red,
        iconTint = Color.Red,
    ),
)

fun getOtherSettings() = listOf(
    ProfileItem(
        "Pengaturan Tema",
        Icons.Default.DarkMode,
        textColor = null,
        intent = SettingIntent.ShowDialogTheme(true)
    ),
)
