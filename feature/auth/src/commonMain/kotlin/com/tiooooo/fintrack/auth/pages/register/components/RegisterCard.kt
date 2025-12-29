package com.tiooooo.fintrack.auth.pages.register.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.mmk.kmpauth.firebase.google.GoogleButtonUiContainerFirebase
import com.tiooooo.fintrack.auth.pages.login.components.OrContinueWithDivider
import com.tiooooo.fintrack.auth.pages.onboard.components.OnboardButton
import com.tiooooo.fintrack.auth.pages.register.RegisterActions
import com.tiooooo.fintrack.auth.pages.register.RegisterState
import com.tiooooo.fintrack.component.component.button.ButtonPrimary
import com.tiooooo.fintrack.component.component.textField.StringTextField
import com.tiooooo.fintrack.component.component.textField.TextFieldPassword
import com.tiooooo.fintrack.component.resources.IconHelper
import com.tiooooo.fintrack.component.theme.LARGE_PADDING
import com.tiooooo.fintrack.component.theme.MEDIUM_PADDING

@Composable
fun RegisterCard(
    modifier: Modifier = Modifier,
    registerActions: RegisterActions,
    registerState: RegisterState,
) = with(registerState) {
    OutlinedCard(
        modifier = modifier
            .clip(RoundedCornerShape(MEDIUM_PADDING)),
        shape = RoundedCornerShape(MEDIUM_PADDING),
        border = BorderStroke(0.dp, MaterialTheme.colorScheme.surface),
    ) {
        Column(
            modifier = Modifier.padding(MEDIUM_PADDING)
        ) {
            StringTextField(
                modifier = Modifier
                    .padding(top = MEDIUM_PADDING)
                    .fillMaxWidth(),
                value = registerFormState.name,
                onValueChange = { registerActions.onNameChanged(it) },
                labelText = "Full name",
                placeHolderText = "e.g: John Doe",
            )
            StringTextField(
                modifier = Modifier
                    .padding(top = MEDIUM_PADDING)
                    .fillMaxWidth(),
                value = registerFormState.email,
                onValueChange = { registerActions.onEmailChanged(it) },
                labelText = "Email",
                placeHolderText = "e.g: user@fintrack.com",
            )
            TextFieldPassword(
                modifier = Modifier
                    .padding(top = MEDIUM_PADDING)
                    .fillMaxWidth(),
                labelText = "Password",
                placeHolderText = "Enter Password",
                value = registerFormState.password,
                onValueChange = { registerActions.onPasswordChanged(it) },
            )
            TextFieldPassword(
                modifier = Modifier
                    .padding(top = MEDIUM_PADDING)
                    .fillMaxWidth(),
                labelText = "Confirm Password",
                placeHolderText = "Confirm Password",
                value = registerFormState.password,
                onValueChange = { registerActions.onPasswordChanged(it) },
            )
            ButtonPrimary(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = MEDIUM_PADDING)
                    .padding(
                        vertical = MEDIUM_PADDING
                    ),
                text = "Register",
                onClick = {
                    registerActions.onNavigate("/dashboard")
                },
                enabled = isButtonEnable
            )

            OrContinueWithDivider(
                modifier = Modifier
                    .padding(top = LARGE_PADDING)
                    .fillMaxWidth()
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = LARGE_PADDING),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                GoogleButtonUiContainerFirebase(
                    linkAccount = false,
                    onResult = { result ->

                    }
                ) {
                    OnboardButton(
                        painter = IconHelper.icLoginGoogle,
                        onCardClicked = {
                            this@GoogleButtonUiContainerFirebase.onClick()
                        }
                    )
                }
            }
        }
    }
}