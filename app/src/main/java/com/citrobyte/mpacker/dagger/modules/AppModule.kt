package com.citrobyte.mpacker.dagger.modules

import android.app.Application
import android.content.Context
import com.citrobyte.mpacker.api.PackerApi
import com.citrobyte.mpacker.dagger.scopes.AppScope
import com.citrobyte.mpacker.utils.ErrorsProcessor
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.citrobyte.mpacker.BuildConfig

@Module
class AppModule {

    @AppScope
    @Provides
    fun provideContext(app: Application): Context {
        return app
    }

    @AppScope
    @Provides
    fun retrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @AppScope
    @Provides
    fun  getApiService(retrofit: Retrofit): PackerApi {
        return retrofit.create(PackerApi::class.java)
    }

    @AppScope
    @Provides
    fun getErrorProcessor(context: Context): ErrorsProcessor {
        return ErrorsProcessor(context)
    }

}