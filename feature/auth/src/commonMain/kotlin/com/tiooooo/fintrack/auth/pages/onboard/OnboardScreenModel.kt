package com.tiooooo.fintrack.auth.pages.onboard

import cafe.adriel.voyager.core.model.ScreenModel
import com.tiooooo.fintrack.component.resources.IconHelper

class OnboardScreenModel : ScreenModel {
  val listOfImages = listOf(
    IconHelper.icOnBoardSort,
    IconHelper.icOnboardData,
    IconHelper.icOnboardHappy
  )

  val listOfText = listOf(
    "Ini teks pertama",
    "Ini teks kedua",
    "Ini teks ketiga",
  )
}