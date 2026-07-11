package com.example.myapplication.features.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private const val COMMENT_BACKDROP_ALPHA = 0.32f
private const val COMMENT_PANEL_ALPHA = 0.95f
private const val COMMENT_FADE_MS = 180
private const val COMMENT_PANEL_ENTER_MS = 260
private const val COMMENT_PANEL_EXIT_MS = 220

@Composable
fun CommentOverlay(
    visible: Boolean,
    onClose: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(animationSpec = tween(durationMillis = COMMENT_FADE_MS)),
            exit = fadeOut(animationSpec = tween(durationMillis = COMMENT_FADE_MS)),
            modifier = Modifier.fillMaxSize(),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = COMMENT_BACKDROP_ALPHA)),
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = {},
                    ),
            )
        }

        AnimatedVisibility(
            visible = visible,
            enter = slideInVertically(animationSpec = tween(durationMillis = COMMENT_PANEL_ENTER_MS)) { it },
            exit = slideOutVertically(animationSpec = tween(durationMillis = COMMENT_PANEL_EXIT_MS)) { it },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
                    .clip(RoundedCornerShape(topStart = 36.dp, topEnd = 36.dp))
                    .background(
                        color = Color.Black.copy(alpha = COMMENT_PANEL_ALPHA),
                        shape = RoundedCornerShape(topStart = 36.dp, topEnd = 36.dp),
                    )
                    .navigationBarsPadding(),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 20.dp),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Box(modifier = Modifier.weight(1f))
                        IconButton(onClick = onClose) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close comments",
                                tint = Color.White,
                            )
                        }
                    }

                    Text(
                        text = "Comment",
                        color = Color.White,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
                    )
                }
            }
        }
    }
}
