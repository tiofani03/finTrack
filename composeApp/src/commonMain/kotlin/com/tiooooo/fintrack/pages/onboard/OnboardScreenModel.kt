package com.tiooooo.fintrack.pages.onboard

import cafe.adriel.voyager.core.model.ScreenModel
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.ic_onboard_data
import fintrack.composeapp.generated.resources.ic_onboard_happy
import fintrack.composeapp.generated.resources.ic_onboard_transfer

class OnboardScreenModel : ScreenModel {
    val listOfImages = listOf(
        Res.drawable.ic_onboard_transfer,
        Res.drawable.ic_onboard_data,
        Res.drawable.ic_onboard_happy
    )

    val listOfText = listOf(
        "Ini teks pertama",
        "Ini teks kedua",
        "Ini teks ketiga",
    )
}