package com.citrobyte.mpacker.dagger.modules

import androidx.lifecycle.ViewModel
import com.citrobyte.mpacker.dagger.scopes.ViewModelKey
import com.citrobyte.mpacker.mvvm.viewmodels.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainActivityViewModel(viewModel: MainActivityViewModel): ViewModel

}