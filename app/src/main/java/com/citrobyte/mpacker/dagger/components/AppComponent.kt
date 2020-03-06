package com.citrobyte.mpacker.dagger.components

import android.app.Application
import com.citrobyte.mpacker.PackerApp
import com.citrobyte.mpacker.dagger.modules.*
import com.citrobyte.mpacker.dagger.scopes.AppScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@AppScope
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBuildersModule::class,
    FragmentsBuildersModule::class,
    ViewModelFactoryModule::class,
    ViewModelsModule::class,
    AppModule::class,
    NetworkModule::class,
    RoomModule::class
])
interface AppComponent : AndroidInjector<PackerApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

}