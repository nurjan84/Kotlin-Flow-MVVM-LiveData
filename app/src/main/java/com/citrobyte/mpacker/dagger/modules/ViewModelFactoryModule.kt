package com.citrobyte.mpacker.dagger.modules

import androidx.lifecycle.ViewModelProvider
import com.citrobyte.mpacker.mvvm.viewmodels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}