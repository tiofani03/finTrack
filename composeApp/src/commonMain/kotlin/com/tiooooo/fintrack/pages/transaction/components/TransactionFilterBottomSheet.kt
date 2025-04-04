package com.tiooooo.fintrack.pages.transaction.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionFilterBottomSheet(
    paddingValues: PaddingValues? = null,
    onClick: (String) -> Unit,
    onDismiss: () -> Unit,
) {
    val modalBottomSheetState = rememberModalBottomSheetState()
    val effectivePadding = paddingValues ?: WindowInsets.systemBars.asPaddingValues()


    ModalBottomSheet(
        modifier = Modifier.padding(top = effectivePadding.calculateTopPadding()),
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            CountryList(
                onClick = onClick
            )
        }
    }
}

@Composable
fun CountryList(
    onClick: (String) -> Unit,
) {
    val countries = listOf(
        Pair("United States", "\uD83C\uDDFA\uD83C\uDDF8"),
        Pair("Canada", "\uD83C\uDDE8\uD83C\uDDE6"),
        Pair("Mexico", "\uD83C\uDDF2\uD83C\uDDFD"),
        Pair("Brazil", "\uD83C\uDDE7\uD83C\uDDF7"),
        Pair("Argentina", "\uD83C\uDDE6\uD83C\uDDF7"),
        Pair("United Kingdom", "\uD83C\uDDEC\uD83C\uDDE7"),
        Pair("France", "\uD83C\uDDEB\uD83C\uDDF7"),
        Pair("Germany", "\uD83C\uDDE9\uD83C\uDDEA"),
        Pair("Italy", "\uD83C\uDDEE\uD83C\uDDF9"),
        Pair("Spain", "\uD83C\uDDEA\uD83C\uDDF8"),
        Pair("Portugal", "\uD83C\uDDF5\uD83C\uDDF9"),
        Pair("Netherlands", "\uD83C\uDDF3\uD83C\uDDF1"),
        Pair("Belgium", "\uD83C\uDDE7\uD83C\uDDEA"),
        Pair("Sweden", "\uD83C\uDDF8\uD83C\uDDEA"),
        Pair("Norway", "\uD83C\uDDF3\uD83C\uDDF4"),
        Pair("Denmark", "\uD83C\uDDE9\uD83C\uDDF0"),
        Pair("Finland", "\uD83C\uDDEB\uD83C\uDDEE"),
        Pair("Russia", "\uD83C\uDDF7\uD83C\uDDFA"),
        Pair("China", "\uD83C\uDDE8\uD83C\uDDF3"),
        Pair("India", "\uD83C\uDDEE\uD83C\uDDF3"),
        Pair("Japan", "\uD83C\uDDEF\uD83C\uDDF5"),
        Pair("South Korea", "\uD83C\uDDF0\uD83C\uDDF7"),
        Pair("Indonesia", "\uD83C\uDDEE\uD83C\uDDE9"),
        Pair("Thailand", "\uD83C\uDDF9\uD83C\uDDED"),
        Pair("Vietnam", "\uD83C\uDDFB\uD83C\uDDF3"),
        Pair("Philippines", "\uD83C\uDDF5\uD83C\uDDED"),
        Pair("Malaysia", "\uD83C\uDDF2\uD83C\uDDFE"),
        Pair("Australia", "\uD83C\uDDE6\uD83C\uDDFA"),
        Pair("New Zealand", "\uD83C\uDDF3\uD83C\uDDFF"),
        Pair("South Africa", "\uD83C\uDDFF\uD83C\uDDE6"),
        Pair("Egypt", "\uD83C\uDDEA\uD83C\uDDEC"),
        Pair("Turkey", "\uD83C\uDDF9\uD83C\uDDF7"),
        Pair("Saudi Arabia", "\uD83C\uDDE6\uD83C\uDDEA"),
        Pair("United Arab Emirates", "\uD83C\uDDE6\uD83C\uDDFE")
    )


    LazyColumn {
        items(countries) { (country, flag) ->
            Row(
                modifier = Modifier
                    .clickable { onClick.invoke(country)  }
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 20.dp)
            ) {
                Text(
                    text = flag,
                    modifier = Modifier.padding(end = 20.dp)
                )
                Text(text = country)
            }
        }
    }
}