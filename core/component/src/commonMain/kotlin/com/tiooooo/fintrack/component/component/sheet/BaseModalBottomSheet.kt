package com.tiooooo.fintrack.component.component.sheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseModalBottomSheet(
    paddingValues: PaddingValues? = null,
    onDismiss: () -> Unit,
    sheetState: SheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    ),
    dragHandle: @Composable (() -> Unit)? = {
        BottomSheetDefaults.DragHandle()
    },
    content: @Composable ColumnScope.() -> Unit
) {
    val effectivePadding =
        paddingValues ?: WindowInsets.systemBars.asPaddingValues()

    ModalBottomSheet(
        modifier = Modifier.padding(
            top = effectivePadding.calculateTopPadding()
        ),
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        dragHandle = dragHandle
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            content()
        }
    }
}
