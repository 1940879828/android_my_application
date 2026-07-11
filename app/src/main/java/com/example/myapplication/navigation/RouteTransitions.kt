package com.example.myapplication.navigation

import androidx.compose.animation.ContentTransform
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.ui.unit.IntOffset
import com.example.myapplication.ui.theme.Motion
import kotlin.math.roundToInt

private const val UNDERLAY_SHIFT_RATIO = 0.28f

private fun pushForwardEnter(): EnterTransition =
    slideInHorizontally(
        animationSpec = Motion.transition<IntOffset>(),
    ) { fullWidth -> fullWidth }

private fun pushForwardExit(): ExitTransition =
    slideOutHorizontally(
        animationSpec = Motion.transition<IntOffset>(),
    ) { fullWidth -> -(fullWidth * UNDERLAY_SHIFT_RATIO).roundToInt() }

private fun pushBackEnter(): EnterTransition =
    slideInHorizontally(
        animationSpec = Motion.transition<IntOffset>(),
    ) { fullWidth -> -(fullWidth * UNDERLAY_SHIFT_RATIO).roundToInt() }

private fun pushBackExit(): ExitTransition =
    slideOutHorizontally(
        animationSpec = Motion.transition<IntOffset>(),
    ) { fullWidth -> fullWidth }

fun resolveTransitionKind(pair: RoutePair): RouteTransitionKind =
    when (pair) {
        RoutePair(AppScreen.HOME, AppScreen.ME) -> RouteTransitionKind.PushForward
        RoutePair(AppScreen.ME, AppScreen.HOME) -> RouteTransitionKind.PushBack

        RoutePair(AppScreen.HOME, AppScreen.CREATE) -> RouteTransitionKind.Fade
        RoutePair(AppScreen.CREATE, AppScreen.HOME) -> RouteTransitionKind.Fade

        else -> RouteTransitionKind.Fade
    }

fun resolveTransition(kind: RouteTransitionKind): ContentTransform =
    when (kind) {
        RouteTransitionKind.PushForward -> {
            (pushForwardEnter() togetherWith pushForwardExit()).apply {
                targetContentZIndex = 1f
            }
        }

        RouteTransitionKind.PushBack -> {
            pushBackEnter() togetherWith pushBackExit()
        }

        RouteTransitionKind.Fade -> {
            fadeIn(animationSpec = Motion.tabFade()) togetherWith
                fadeOut(animationSpec = Motion.tabFade())
        }

        RouteTransitionKind.None -> {
            EnterTransition.None togetherWith ExitTransition.None
        }
    }

fun resolveTransition(from: AppScreen, to: AppScreen): ContentTransform =
    resolveTransition(resolveTransitionKind(RoutePair(from, to)))
