package com.example.myapplication.core

import com.example.myapplication.navigation.AppScreen
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class AppViewModelTest {

    @Test
    fun openComments_marksCommentSheetVisible() {
        val viewModel = AppViewModel()

        viewModel.openComments()

        assertTrue(viewModel.isCommentSheetOpen.value)
    }

    @Test
    fun navigateBack_closesCommentSheetBeforeChangingScreen() {
        val viewModel = AppViewModel()
        viewModel.openMe()
        viewModel.openComments()

        viewModel.navigateBack()

        assertFalse(viewModel.isCommentSheetOpen.value)
        assertEquals(AppScreen.ME, viewModel.screen.value)
    }

    @Test
    fun openingAnotherRootScreen_closesCommentSheet() {
        val viewModel = AppViewModel()
        viewModel.openComments()

        viewModel.openCreate()

        assertFalse(viewModel.isCommentSheetOpen.value)
        assertEquals(AppScreen.CREATE, viewModel.screen.value)
    }
}
