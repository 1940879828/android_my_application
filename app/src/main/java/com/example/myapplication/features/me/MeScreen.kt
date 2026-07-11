package com.example.myapplication.features.me

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MeScreen (
    onBack: () -> Unit,
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
    ) {
        MeGridBackground()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TextButton(onClick = onBack) {
                Text(
                    text = "Back",
                    color = Color.White,
                    fontSize = 16.sp,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
        }
        Text(
            text = "Me",
            color = Color.White,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center),
        )
    }
}

@Composable
private fun MeGridBackground() {
    val lineColor = Color(0xFF8B5E3C).copy(alpha = 0.82f)
    val accentColor = Color(0xFFD1A472).copy(alpha = 0.28f)

    Canvas(modifier = Modifier.fillMaxSize()) {
        val step = 32.dp.toPx()
        val halfStep = step / 2f
        val stroke = 1.dp.toPx()
        val dash = PathEffect.dashPathEffect(floatArrayOf(8.dp.toPx(), 6.dp.toPx()))

        var x = 0f
        while (x <= size.width) {
            drawLine(
                color = lineColor,
                start = Offset(x, 0f),
                end = Offset(x, size.height),
                strokeWidth = stroke,
            )
            x += step
        }

        var y = 0f
        var row = 0
        while (y <= size.height) {
            drawLine(
                color = lineColor,
                start = Offset(0f, y),
                end = Offset(size.width, y),
                strokeWidth = stroke,
            )

            val startX = if (row % 2 == 0) halfStep else 0f
            var crossX = startX
            while (crossX <= size.width) {
                drawLine(
                    color = accentColor,
                    start = Offset(crossX - 7.dp.toPx(), y + 7.dp.toPx()),
                    end = Offset(crossX + 7.dp.toPx(), y - 7.dp.toPx()),
                    strokeWidth = stroke,
                    pathEffect = dash,
                )
                crossX += step
            }

            y += step
            row += 1
        }
    }
}