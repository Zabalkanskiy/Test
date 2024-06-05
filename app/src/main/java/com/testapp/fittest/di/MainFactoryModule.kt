package com.testapp.fittest.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.testapp.fittest.feature.main_screen.presentation.view_model.MainScreenViewModel
import com.testapp.fittest.feature.setting_screen.presentation.view_model.SettingScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainFactoryModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainScreenViewModel::class)
    fun bindMainScreenViewModel(mainScreenViewModel: MainScreenViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SettingScreenViewModel::class)
    fun bindSettingScreenViewModel(settingScreenViewModel: SettingScreenViewModel): ViewModel

    @Binds
    fun bindViewModelsFactory(viewModelsFactory: CoreViewModelFactory): ViewModelProvider.Factory

}