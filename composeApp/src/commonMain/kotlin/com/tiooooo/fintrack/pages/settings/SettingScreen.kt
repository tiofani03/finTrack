package com.tiooooo.fintrack.pages.settings

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.automirrored.filled.HelpCenter
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
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
import com.tiooooo.fintrack.component.theme.MEDIUM_PADDING
import com.tiooooo.fintrack.component.theme.SMALL_PADDING
import com.tiooooo.fintrack.component.theme.textMedium10
import com.tiooooo.fintrack.component.theme.textMedium14
import com.tiooooo.fintrack.component.theme.textMedium16
import com.tiooooo.fintrack.pages.settings.component.ChooseThemeDialog
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.ic_login_google
import org.jetbrains.compose.resources.painterResource

@Composable
fun SettingScreen(
    modifier: Modifier = Modifier,
    settingScreenModel: SettingScreenModel,
) {
    val listState by settingScreenModel.lazyListState.collectAsState()
    val navigator = LocalNavigator.currentOrThrow
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
                item { SectionList(title = "Akun", items = getDummyAccountItems()) }
                item { SectionList(title = "Bantuan", items = getDummyHelpItems()) }
                item { SectionList(title = "Kebijakan dan Privasi", items = getTermAndCondition()) }
                item {
                    if (state.isEnableDarkMode) {
                        SectionList(
                            title = "Lainnya",
                            items = getOtherSettings(),
                            onItemClicked = { item ->
                                item.intent?.let {
                                    settingScreenModel.dispatch(it)
                                }
                            })
                    }
                }
                item {
                    SectionList(
                        title = "",
                        items = listOf(
                            ProfileItem(
                                "Logout",
                                null,
                                null,
                                SettingIntent.ExecuteLogout
                            ),
                        ),
                        onItemClicked = { item ->
                            item.intent?.let {
                                settingScreenModel.dispatch(it)
                            }
                        }
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
                Image(
                    painter = painterResource(Res.drawable.ic_login_google),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                )
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
    title: String,
    items: List<ProfileItem>,
    onItemClicked: (ProfileItem) -> Unit = { },
) {
    Column(
        modifier = Modifier
    ) {
        Text(
            modifier = Modifier.padding(start = MEDIUM_PADDING, top = SMALL_PADDING),
            text = title,
            style = textMedium16().copy(
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(SMALL_PADDING))

        items.forEach { item ->
            ProfileListItem(item, onItemClicked = onItemClicked)
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
        item.icon?.let {
            Icon(imageVector = item.icon, contentDescription = item.title)
        }
        Spacer(modifier = Modifier.width(MEDIUM_PADDING))

        Text(
            text = item.title,
            style = textMedium14().copy(
                fontWeight = FontWeight.Light
            ),
            modifier = Modifier.weight(1f)
        )

        if (item.actionText != null) {
            Text(
                text = item.actionText,
                style = textMedium10().copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                ),
                modifier = Modifier
                    .clip(RoundedCornerShape(MEDIUM_PADDING))
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(horizontal = MEDIUM_PADDING, vertical = SMALL_PADDING)
            )
        } else {
            Icon(imageVector = Icons.Default.ChevronRight, contentDescription = "Next")
        }
    }
}


data class ProfileItem(
    val title: String,
    val icon: ImageVector? = null,
    val actionText: String? = null,
    val intent: SettingIntent? = null
)

fun getDummyAccountItems() = listOf(
    ProfileItem("Akun Premium", Icons.Default.Star, "Upgrade"),
)

fun getDummyHelpItems() = listOf(
    ProfileItem("Pusat Bantuan", Icons.AutoMirrored.Filled.Help),
    ProfileItem("Tanya dan Jawab", Icons.AutoMirrored.Filled.HelpCenter)
)

fun getTermAndCondition() = listOf(
    ProfileItem("Ketentuan Layanan", Icons.Default.Info),
    ProfileItem("Kebijakan Privasi", Icons.Default.Key),
)

fun getOtherSettings() = listOf(
    ProfileItem(
        "Pengaturan Tema",
        Icons.Default.DarkMode,
        null,
        SettingIntent.ShowDialogTheme(true)
    ),
)
