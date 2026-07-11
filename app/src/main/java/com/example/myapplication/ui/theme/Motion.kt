package com.example.myapplication.ui.theme

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween

object Motion {
    const val TAB_FADE_MS = 180

    val EaseOut = CubicBezierEasing(0f, 0f, 0.58f, 1f)
    val EaseInOut = CubicBezierEasing(0.42f, 0f, 0.58f, 1f)

    fun <T> tabFade(): TweenSpec<T> = tween(
        durationMillis = TAB_FADE_MS,
        easing = EaseOut,
    )

    fun <T> transition(): FiniteAnimationSpec<T> = spring(
        dampingRatio = 0.88f,
        stiffness = 224f,
    )
}