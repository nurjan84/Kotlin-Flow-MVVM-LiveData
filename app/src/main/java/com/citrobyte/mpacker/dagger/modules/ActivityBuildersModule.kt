package com.citrobyte.mpacker.dagger.modules

import com.citrobyte.mpacker.mvvm.views.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}