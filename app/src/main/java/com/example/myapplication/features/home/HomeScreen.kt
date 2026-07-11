package com.example.myapplication.features.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.geometry.Offset

@Composable
fun HomeScreen(
    onOpenMe: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
    ) {
        HomeGridBackground()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(modifier = Modifier.weight(1f))
            TextButton(onClick = onOpenMe) {
                Text(
                    text = "Me",
                    color = Color.White,
                    fontSize = 16.sp,
                )
            }
        }
        Text(
            text = "Home",
            color = Color.White,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center),
        )
    }
}

@Composable
private fun HomeGridBackground() {
    val lineColor = Color(0xFF9B4DFF).copy(alpha = 0.72f)
    val accentColor = Color(0xFFCF9CFF).copy(alpha = 0.30f)

    Canvas(modifier = Modifier.fillMaxSize()) {
        val step = 32.dp.toPx()
        val halfStep = step / 2f
        val stroke = 1.dp.toPx()
        val dash = PathEffect.dashPathEffect(floatArrayOf(10.dp.toPx(), 6.dp.toPx()))

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

            val startX = if (row % 2 == 0) 0f else halfStep
            var crossX = startX
            while (crossX <= size.width) {
                drawLine(
                    color = accentColor,
                    start = Offset(crossX - 8.dp.toPx(), y - 8.dp.toPx()),
                    end = Offset(crossX + 8.dp.toPx(), y + 8.dp.toPx()),
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