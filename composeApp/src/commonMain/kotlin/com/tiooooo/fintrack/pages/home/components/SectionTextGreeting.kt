package com.tiooooo.fintrack.pages.home.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.tiooooo.fintrack.component.theme.textMedium10
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock

@Composable
fun SectionTextGreeting(
    modifier: Modifier = Modifier,
    name: String,
) {
    val greeting = remember {
        val now = Clock.System.now()
        val localTime = now.toLocalDateTime(TimeZone.currentSystemDefault()).time
        val hour = localTime.hour

        when (hour) {
            in 4..10 -> "Selamat pagi"
            in 11..14 -> "Selamat siang"
            in 15..17 -> "Selamat sore"
            else -> "Selamat malam"
        }
    }

    Text(
        modifier = modifier,
        text = "$greeting $name 👋",
        style = textMedium10().copy(
            fontWeight = FontWeight.ExtraLight
        )
    )
}
