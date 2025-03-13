package com.tiooooo.fintrack.component.base

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass

@Composable
fun MultiWindowSizeLayout(
    default: @Composable () -> Unit,
    expanded: (@Composable () -> Unit)? = null,
    portrait: (@Composable () -> Unit)? = null,
    portraitTablet: (@Composable () -> Unit)? = null,
    portraitPhone: (@Composable () -> Unit)? = null,
    desktop: (@Composable () -> Unit)? = null,
) {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    windowSizeClass.display(
        default,
        expanded ?: default,
        portrait ?: default,
        portraitTablet,
        portraitPhone,
        desktop,
    )
}

@Composable
fun WindowSizeClass.display(
    default: @Composable () -> Unit,
    expanded: @Composable () -> Unit,
    portraitMulti: @Composable () -> Unit,
    portraitTablet: (@Composable () -> Unit)? = null,
    portraitPhone: (@Composable () -> Unit)? = null,
    desktop: (@Composable () -> Unit)? = null,
) {
    when {
        (windowWidthSizeClass == WindowWidthSizeClass.COMPACT &&
                windowHeightSizeClass in listOf(
            WindowHeightSizeClass.MEDIUM,
            WindowHeightSizeClass.EXPANDED
        )) -> {
            portraitPhone?.invoke() ?: portraitMulti()
        }

        (windowWidthSizeClass == WindowWidthSizeClass.MEDIUM &&
                windowHeightSizeClass == WindowHeightSizeClass.EXPANDED) -> {
            portraitTablet?.invoke() ?: portraitMulti()
        }

        windowWidthSizeClass == WindowWidthSizeClass.EXPANDED &&
                windowHeightSizeClass == WindowHeightSizeClass.EXPANDED -> {
            expanded()
        }

        windowWidthSizeClass == WindowWidthSizeClass.EXPANDED &&
                windowHeightSizeClass == WindowHeightSizeClass.MEDIUM -> {
            desktop?.invoke()
        }

        else -> {
            default()
        }
    }
}