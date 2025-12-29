package com.tiooooo.fintrack.pages.home.components

import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.patrykandpatrick.vico.multiplatform.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.multiplatform.cartesian.axis.HorizontalAxis
import com.patrykandpatrick.vico.multiplatform.cartesian.axis.VerticalAxis
import com.patrykandpatrick.vico.multiplatform.cartesian.axis.rememberAxisGuidelineComponent
import com.patrykandpatrick.vico.multiplatform.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.multiplatform.cartesian.data.CartesianLayerRangeProvider
import com.patrykandpatrick.vico.multiplatform.cartesian.data.CartesianValueFormatter
import com.patrykandpatrick.vico.multiplatform.cartesian.data.lineSeries
import com.patrykandpatrick.vico.multiplatform.cartesian.layer.LineCartesianLayer
import com.patrykandpatrick.vico.multiplatform.cartesian.layer.rememberLine
import com.patrykandpatrick.vico.multiplatform.cartesian.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.multiplatform.cartesian.marker.CartesianMarker
import com.patrykandpatrick.vico.multiplatform.cartesian.marker.DefaultCartesianMarker
import com.patrykandpatrick.vico.multiplatform.cartesian.marker.rememberDefaultCartesianMarker
import com.patrykandpatrick.vico.multiplatform.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.multiplatform.cartesian.rememberVicoScrollState
import com.patrykandpatrick.vico.multiplatform.common.Fill
import com.patrykandpatrick.vico.multiplatform.common.Insets
import com.patrykandpatrick.vico.multiplatform.common.LayeredComponent
import com.patrykandpatrick.vico.multiplatform.common.component.ShapeComponent
import com.patrykandpatrick.vico.multiplatform.common.component.TextComponent
import com.patrykandpatrick.vico.multiplatform.common.component.rememberShapeComponent
import com.patrykandpatrick.vico.multiplatform.common.component.rememberTextComponent
import com.patrykandpatrick.vico.multiplatform.common.fill
import com.patrykandpatrick.vico.multiplatform.common.shape.CorneredShape
import com.patrykandpatrick.vico.multiplatform.common.shape.MarkerCorneredShape
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.toLocalDateTime
import kotlin.math.roundToInt
import kotlin.time.Clock


@Composable
internal fun rememberMarker(
    valueFormatter: DefaultCartesianMarker.ValueFormatter =
        DefaultCartesianMarker.ValueFormatter.default(),
    showIndicator: Boolean = true,
): CartesianMarker {
    val labelBackgroundShape = MarkerCorneredShape(CorneredShape.Corner.Rounded)
    val labelBackground =
        rememberShapeComponent(
            fill = fill(MaterialTheme.colorScheme.background),
            shape = labelBackgroundShape,
            strokeFill = fill(MaterialTheme.colorScheme.outline),
            strokeThickness = 1.dp,
        )
    val label =
        rememberTextComponent(
            style =
                TextStyle(
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                ),
            padding = Insets(8.dp, 4.dp),
            background = labelBackground,
            minWidth = TextComponent.MinWidth.fixed(40.dp),
        )
    val indicatorFrontComponent =
        rememberShapeComponent(fill(MaterialTheme.colorScheme.surface), CorneredShape.Pill)
    val guideline = rememberAxisGuidelineComponent()
    return rememberDefaultCartesianMarker(
        label = label,
        valueFormatter = valueFormatter,
        indicator =
            if (showIndicator) {
                { color ->
                    LayeredComponent(
                        back = ShapeComponent(Fill(color.copy(alpha = 0.15f)), CorneredShape.Pill),
                        front =
                            LayeredComponent(
                                back = ShapeComponent(
                                    fill = Fill(color),
                                    shape = CorneredShape.Pill
                                ),
                                front = indicatorFrontComponent,
                                padding = Insets(5.dp),
                            ),
                        padding = Insets(10.dp),
                    )
                }
            } else {
                null
            },
        indicatorSize = 36.dp,
        guideline = guideline,
    )
}

@Composable
fun FinancialInsightChartNew(modifier: Modifier = Modifier) {
    val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    val last6Months = (0 until 6).map {
        now.minus(DatePeriod(months = 5 - it))
    }

    val x = (0 until 6).toList()
    val y = listOf<Number>(523000.0, 1400000, 300000, 5800000, 1500000.0, 400000.0)

    val modelProducer = remember { CartesianChartModelProducer() }

    LaunchedEffect(Unit) {
        modelProducer.runTransaction {
            lineSeries { series(x, y) }
        }
    }

    val lineColor = MaterialTheme.colorScheme.primary

    val monthLabels = last6Months.map {
        it.month.name.lowercase()
            .replaceFirstChar { char -> char.uppercase() }
            .take(3)
    }

    val monthFormatter = CartesianValueFormatter { _, value, _ ->
        monthLabels.getOrElse(value.toInt()) { "" }
    }

    val currencyShortFormatter = CartesianValueFormatter { _, value, _ ->
        formatCurrencyShort(value)
    }

    val markerFormatter = DefaultCartesianMarker.ValueFormatter { _, targets ->
        val xIndex = targets.firstOrNull()?.x?.toInt() ?: return@ValueFormatter ""
        val newValue = y.getOrNull(xIndex) ?: return@ValueFormatter ""
        formatCurrencyShort(newValue.toDouble())

    }

    CartesianChartHost(
        chart = rememberCartesianChart(
            rememberLineCartesianLayer(
                lineProvider = LineCartesianLayer.LineProvider.series(
                    LineCartesianLayer.rememberLine(
                        fill = LineCartesianLayer.LineFill.single(fill(lineColor)),
                        areaFill = LineCartesianLayer.AreaFill.single(
                            fill(
                                Brush.verticalGradient(
                                    listOf(lineColor.copy(alpha = 0.4f), Color.Transparent)
                                )
                            )
                        )
                    )
                ),
                rangeProvider = CartesianLayerRangeProvider.auto()
            ),
            startAxis = VerticalAxis.rememberStart(valueFormatter = currencyShortFormatter),
            bottomAxis = HorizontalAxis.rememberBottom(valueFormatter = monthFormatter),
            marker = rememberMarker(markerFormatter),
        ),
        modelProducer = modelProducer,
        modifier = modifier.height(216.dp),
        scrollState = rememberVicoScrollState(scrollEnabled = false)
    )
}

fun formatCurrencyShort(value: Double): String {
    return when {
        value >= 1_000_000 -> "${(value / 1_000_000 * 10).roundToInt() / 10.0}jt"
        value >= 1_000 -> "${(value / 1_000 * 10).roundToInt() / 10.0}k"
        else -> value.roundToInt().toString()
    }
}
