package com.tiooooo.fintrack.data.user.api.model

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

@Serializable
data class User(
  val id: String = "",
  val firstName: String = "",
  val lastName: String = "",
  val email: String = "",
  val photoUrl: String? = null,
  val createdAt: Instant = Clock.System.now(),

  // App Metadata
  val defaultCurrency: String = "IDR",
  val defaultWalletId: String? = null,
  val language: String = "id",
  val isPremium: Boolean = false,
  val lastActiveAt: Instant? = null,

  // Feature Toggles or Settings
  val notificationEnabled: Boolean = true,
  val darkMode: Boolean = false,

  // Optional: analytics or tracking
  val installSource: String? = null,
  val deviceType: String? = null,
)

@Serializable
data class Wallet(
  val name: String = "Default Wallet",
  val type: String = "cash",
  val balance: Double = 0.0,
  val createdAt: Instant = Clock.System.now(),
)


@Serializable
data class Transaction(
  val id: String = "",
  val walletId: String = "",
  val amount: Double = 0.0,
  val type: String = "expense",
  val category: String? = null,
  val description: String? = null,
  val date: Instant = Clock.System.now(),
  val createdAt: Instant = Clock.System.now(),
)

@Serializable
data class Category(
  val id: String = "",
  val name: String = "",
  val type: String = "expense",
  val icon: String? = null,
  val isDefault: Boolean = false,
  val createdAt: Instant = Clock.System.now(),
)