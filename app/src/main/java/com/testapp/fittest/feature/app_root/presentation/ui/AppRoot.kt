package com.testapp.fittest.feature.app_root.presentation.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.testapp.fittest.feature.main_screen.presentation.ui.MainScreen
import com.testapp.fittest.feature.main_screen.presentation.view_model.MainScreenViewModel
import com.testapp.fittest.feature.setting_screen.presentation.ui.SettingScreen
import com.testapp.fittest.feature.setting_screen.presentation.view_model.SettingScreenViewModel
import com.testapp.fittest.utils.NavigationDestination

@Composable
fun AppRoot(mainScreenViewModel: MainScreenViewModel, settingScreenViewModel: SettingScreenViewModel) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = NavigationDestination.MainScreen.destination) {
        composable(NavigationDestination.MainScreen.destination) {
            MainScreen(mainScreenViewModel = mainScreenViewModel )
        }
        composable(NavigationDestination.SettingScreen.destination) {
            SettingScreen(settingScreenViewModel = settingScreenViewModel)
        }
    }
}