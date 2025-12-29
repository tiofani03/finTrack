package com.tiooooo.fintrack.data.wallet.api.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import kotlin.time.Clock

@Serializable
data class Wallet(
  val id: String = "",
  val name: String = "Default Wallet",
  val type: String = "cash",
  val balance: Double = 0.0,
  val color: Int = Color.White.toArgb(),
  val createdAt: Instant = Clock.System.now(),
  val updatedAt: Instant = Clock.System.now(),
)