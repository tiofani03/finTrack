package com.tiooooo.fintrack.feature.settings.pages.settings.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tiooooo.fintrack.component.component.button.ButtonSecondarySmall
import com.tiooooo.fintrack.component.resources.IconHelper
import com.tiooooo.fintrack.component.theme.EXTRA_SMALL_PADDING
import com.tiooooo.fintrack.component.theme.LARGE_PADDING
import com.tiooooo.fintrack.component.theme.MEDIUM_PADDING
import com.tiooooo.fintrack.component.theme.textMedium14
import com.tiooooo.fintrack.component.theme.textMedium16
import org.jetbrains.compose.resources.painterResource

@Composable
fun SettingProfileHeader(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(IconHelper.icLoginGoogle),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
        )
        Text(
            modifier = Modifier.padding(top = LARGE_PADDING),
            text = "Tio Fani",
            style = textMedium16().copy(
                fontWeight = FontWeight.Bold
            ),
        )
        Text(
            modifier = Modifier.padding(top = EXTRA_SMALL_PADDING),
            text = "fanitio21@gmail.com",
            style = textMedium14().copy(
                fontWeight = FontWeight.Light,
            )
        )
        ButtonSecondarySmall(
            modifier = Modifier
                .padding(top = LARGE_PADDING)
                .wrapContentWidth(),
            text = "Edit Profile",
            onClick = {

            }
        )
        Spacer(modifier = Modifier.padding(bottom = MEDIUM_PADDING))
    }
}