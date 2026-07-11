package com.example.myapplication.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.myapplication.core.AppViewModel
import com.example.myapplication.features.create.CreateScreen
import com.example.myapplication.features.home.HomeScreen
import com.example.myapplication.features.me.MeScreen

@Composable
fun NavGraph(
    viewModel: AppViewModel,
) {
    val currentScreen by viewModel.screen.collectAsState()

    BackHandler(enabled = currentScreen != AppScreen.HOME) {
        viewModel.navigateBack()
    }

    // 关键：最底层先铺黑底，动画过程中即使出现 1~几 px 缝，也不会露出白色窗口背景
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
    ) {
        AnimatedContent(
            targetState = currentScreen,
            modifier = Modifier.fillMaxSize(),
            transitionSpec = {
                resolveTransition(
                    from = initialState,
                    to = targetState,
                )
            },
            label = "root-screen",
        ) { screen ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black),
            ) {
                when (screen) {
                    AppScreen.HOME -> HomeScreen(
                        onOpenCreate = { viewModel.openCreate() },
                        onOpenMe = { viewModel.openMe() },
                    )

                    AppScreen.CREATE -> CreateScreen(
                        onBack = { viewModel.navigateBack() },
                    )

                    AppScreen.ME -> MeScreen(
                        onBack = { viewModel.navigateBack() },
                    )
                }
            }
        }
    }
}