package com.testapp.fittest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.testapp.fittest.di.DaggerMainComponent
import com.testapp.fittest.feature.main_screen.presentation.ui.MainScreen
import com.testapp.fittest.feature.main_screen.presentation.view_model.MainScreenViewModel
import com.testapp.fittest.feature.setting_screen.presentation.ui.SettingScreen
import com.testapp.fittest.feature.setting_screen.presentation.view_model.SettingScreenViewModel
import com.testapp.fittest.ui.theme.FitTestTheme
import com.testapp.fittest.utils.NavigationDestination
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var mainScreenViewModel: MainScreenViewModel
    lateinit var settingScreenViewModel: SettingScreenViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        DaggerMainComponent.builder().build().inject(this)
        mainScreenViewModel =  ViewModelProvider(this, viewModelFactory).get(MainScreenViewModel::class.java)
        settingScreenViewModel = ViewModelProvider(this, viewModelFactory).get(SettingScreenViewModel::class.java)
        setContent {
            val navController = rememberNavController()

            val items = listOf(
                NavigationDestination.MainScreen,
                NavigationDestination.SettingScreen,
            )

            FitTestTheme {
                Scaffold (
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomNavigation {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination
                            items.forEach { screen ->
                                BottomNavigationItem(
                                    icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
                                    label = { Text(screen.destination) },
                                    selected = currentDestination?.hierarchy?.any { it.route == screen.destination } == true,
                                    onClick = {
                                        navController.navigate(screen.destination) {
                                            // Pop up to the start destination of the graph to
                                            // avoid building up a large stack of destinations
                                            // on the back stack as users select items
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            // Avoid multiple copies of the same destination when
                                            // reselecting the same item
                                            launchSingleTop = true
                                            // Restore state when reselecting a previously selected item
                                            restoreState = true
                                        }
                                    }
                                )
                            }
                        }
                    }
                    ){ innerPadding ->
                    NavHost(navController, startDestination = NavigationDestination.MainScreen.destination, Modifier.padding(innerPadding)) {
                        composable(NavigationDestination.MainScreen.destination) {
                            MainScreen(mainScreenViewModel = mainScreenViewModel)
                        }
                        composable(NavigationDestination.SettingScreen.destination) {
                            SettingScreen(settingScreenViewModel = settingScreenViewModel)
                        }
                    }
                }
            }

        }
    }
}