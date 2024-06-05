package com.testapp.fittest.di

import com.testapp.fittest.MainActivity
import dagger.Component

@Component(modules = [MainFactoryModule::class])
interface MainComponent {

    fun inject(mainActivity: MainActivity)
}