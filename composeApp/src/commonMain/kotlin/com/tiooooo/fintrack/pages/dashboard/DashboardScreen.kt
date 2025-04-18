package com.tiooooo.fintrack.pages.dashboard

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.tiooooo.fintrack.component.base.BaseScaffold
import com.tiooooo.fintrack.component.component.bottomNavigation.BottomNavItem
import com.tiooooo.fintrack.component.theme.MEDIUM_PADDING
import com.tiooooo.fintrack.pages.detail.DetailRoute
import com.tiooooo.fintrack.pages.home.HomeScreen
import com.tiooooo.fintrack.pages.home.HomeScreenModel
import com.tiooooo.fintrack.pages.settings.SettingScreen
import com.tiooooo.fintrack.pages.settings.SettingScreenModel
import com.tiooooo.fintrack.pages.transaction.TransactionScreen
import com.tiooooo.fintrack.pages.transaction.TransactionScreenModel
import com.tiooooo.fintrack.pages.wallet.list.WalletScreen
import com.tiooooo.fintrack.pages.wallet.list.WalletScreenModel

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    screenModel: DashboardScreenModel,
    homeScreenModel: HomeScreenModel,
    walletScreenModel: WalletScreenModel,
    transactionScreenModel: TransactionScreenModel,
    settingScreenModel: SettingScreenModel,
) {
    val showingBottomBar by remember { mutableStateOf(true) }

    val bottomNavList by screenModel.bottomNavList.collectAsState()
    val bottomSelectedItem by screenModel.bottomSheetPosition.collectAsState()
    val navigator = LocalNavigator.currentOrThrow

    BaseScaffold(
        modifier = modifier,
        bottomBar = {
            AnimatedVisibility(
                visible = showingBottomBar,
                enter = slideInVertically { height -> height },
                exit = slideOutVertically { height -> height }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 36.dp)
                ) {
                    ElevatedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .clip(
                                RoundedCornerShape(
                                    topStart = MEDIUM_PADDING,
                                    topEnd = MEDIUM_PADDING
                                )
                            ),
                        shape = RoundedCornerShape(
                            topStart = MEDIUM_PADDING,
                            topEnd = MEDIUM_PADDING
                        )
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            bottomNavList.forEachIndexed { _, bottomNavModel ->
                                BottomNavItem(
                                    modifier = Modifier.weight(1f),
                                    bottomNavModel = bottomNavModel,
                                    isSelected = bottomSelectedItem == bottomNavModel.slug
                                ) { screenModel.updateBottomSheetPosition(bottomNavModel.slug) }
                            }
                        }
                    }
                    FloatingActionButton(
                        onClick = {
                            navigator.push(DetailRoute("Halo ges"))
                        },
                        modifier = Modifier
                            .align(Alignment.Center)
                            .offset(y = (-36).dp)
                            .clip(CircleShape),
                        containerColor = MaterialTheme.colorScheme.primary,
                        elevation = FloatingActionButtonDefaults.elevation(defaultElevation = MEDIUM_PADDING),
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add",
                            modifier = Modifier.size(32.dp),
                        )
                    }
                }
            }

        }
    ) { paddingValues ->
        when (bottomSelectedItem) {
            BottomNavTarget.HOME -> {
                HomeScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    homeScreenModel = homeScreenModel,
                    onTransactionClicked = {
                        screenModel.updateBottomSheetPosition(BottomNavTarget.TRANSACTIONS)
                    }
                )
            }
            BottomNavTarget.WALLET -> {
                WalletScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = paddingValues.calculateBottomPadding()),
                    walletScreenModel = walletScreenModel,
                )
            }

            BottomNavTarget.TRANSACTIONS -> {
                TransactionScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    transactionScreenModel = transactionScreenModel,
                )
            }

            BottomNavTarget.SETTINGS -> {
                SettingScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = paddingValues.calculateBottomPadding()),
                    settingScreenModel = settingScreenModel,
                )
            }
        }
    }
}
