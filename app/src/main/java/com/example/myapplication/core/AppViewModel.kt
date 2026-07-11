package com.example.myapplication.core

import androidx.lifecycle.ViewModel
import com.example.myapplication.navigation.AppScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Home / Me 页面发出“导航意图”
 * -> AppViewModel 改全局 screen 状态
 * -> NavGraph 观察到 screen 从 A 变成 B
 * -> NavGraph 根据 from / to 选择转场动画
 * -> AnimatedContent 渲染目标页面
 *
 * 点击按钮
 * -> 回调触发
 * -> ViewModel 改 screen
 * -> Compose 检测到状态变化
 * -> NavGraph 重组
 * -> 动画执行
 * -> 目标页面出现
 */
class AppViewModel : ViewModel() {
    private val _screen = MutableStateFlow(AppScreen.HOME)
    val screen: StateFlow<AppScreen> = _screen.asStateFlow()
    private val _isCommentSheetOpen = MutableStateFlow(false)
    val isCommentSheetOpen: StateFlow<Boolean> = _isCommentSheetOpen.asStateFlow()

    fun openComments() {
        _isCommentSheetOpen.value = true
    }

    fun closeComments() {
        _isCommentSheetOpen.value = false
    }

    fun openMe() {
        closeComments()
        _screen.value = AppScreen.ME
    }

    fun openCreate() {
        closeComments()
        _screen.value = AppScreen.CREATE
    }
    fun navigateBack() {
        when {
            _isCommentSheetOpen.value -> closeComments()
            else -> when (_screen.value) {
                AppScreen.HOME -> Unit
                AppScreen.CREATE -> _screen.value = AppScreen.HOME
                AppScreen.ME -> _screen.value = AppScreen.HOME
            }
        }
    }
}