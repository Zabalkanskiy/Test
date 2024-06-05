package com.testapp.fittest.feature.main_screen.presentation.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.testapp.fittest.feature.main_screen.presentation.view_model.MainScreenViewModel

@Composable
fun MainScreen( mainScreenViewModel: MainScreenViewModel) {
    Text(text = "Main Screen")
}