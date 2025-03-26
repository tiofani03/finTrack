package com.tiooooo.fintrack.pages.transaction.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tiooooo.fintrack.component.theme.MEDIUM_PADDING
import com.tiooooo.fintrack.component.theme.SMALL_PADDING
import com.tiooooo.fintrack.component.theme.textMedium12
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.ic_onboard_sort
import org.jetbrains.compose.resources.painterResource

@Composable
fun TransactionSearchBar(
    modifier: Modifier = Modifier,
    offsetY: Dp,
    searchQuery: String,
    onValueChange: (String) -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            modifier = Modifier
                .weight(1f)
                .padding(start = MEDIUM_PADDING)
                .offset(y = offsetY),
            value = searchQuery,
            textStyle = textMedium12(),
            onValueChange = { onValueChange.invoke(it) },
            shape = RoundedCornerShape(MEDIUM_PADDING),
            placeholder = {
                Text(
                    text = "Cari Transkasi",
                    style = textMedium12(),
                    maxLines = 1,
                )
            },
            singleLine = true,
            visualTransformation = VisualTransformation.None,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Gray,
                selectionColors = TextSelectionColors(
                    backgroundColor = Color.Transparent,
                    handleColor = Color.Transparent,
                ),
            ),
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = {
                        onValueChange.invoke("")
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = null,
                        )
                    }
                }
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = null,
                )
            },
        )
        IconButton(
            modifier = Modifier
                .width(48.dp)
                .height(48.dp)
                .align(Alignment.CenterVertically),
            onClick = {},
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_onboard_sort),
                contentDescription = null,
                modifier = Modifier.padding(SMALL_PADDING),
            )
        }
    }
}
