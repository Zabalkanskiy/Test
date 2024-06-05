package com.testapp.fittest.utils

sealed class NavigationDestination(val destination: String) {
    object MainScreen : NavigationDestination("main_screen")
    object SettingScreen : NavigationDestination("setting_screen")
}