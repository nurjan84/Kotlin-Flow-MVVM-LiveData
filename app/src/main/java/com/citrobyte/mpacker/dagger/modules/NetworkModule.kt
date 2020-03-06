package com.citrobyte.mpacker.dagger.modules

import android.content.Context
import com.citrobyte.mpacker.BuildConfig
import com.citrobyte.mpacker.api.CacheProviders
import com.citrobyte.mpacker.dagger.scopes.AppScope
import com.citrobyte.mpacker.room.dao.UserDao
import com.citrobyte.mpacker.utils.Logger
import com.epam.coroutinecache.api.CacheParams
import com.epam.coroutinecache.api.CoroutinesCache
import com.epam.coroutinecache.mappers.GsonMapper
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import java.net.HttpURLConnection
import java.util.concurrent.TimeUnit
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import kotlinx.coroutines.flow.flow
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform


@Module
class NetworkModule {

    @AppScope
    @Provides
    fun coroutineCache(context: Context): CacheProviders {
       val coroutineCache = CoroutinesCache(CacheParams(10, GsonMapper(), context.cacheDir))
       return coroutineCache.using<CacheProviders>(CacheProviders::class.java)
    }

    @AppScope
    @Provides
    fun interceptor(userDao: UserDao): Interceptor {
        return Interceptor { chain ->

            val request = chain.request()
            val requestBuilder = request.newBuilder()

            requestBuilder.addHeader("Accept", "application/json;version=v1.0;charset=utf-8")
            val token:String? = userDao.getUser()?.token
            Logger.i("token = $token")
            requestBuilder.addHeader("Authorization", token ?: "" )
            val response = chain.proceed(requestBuilder.build())

            val code = response.code()
            if(code == HttpURLConnection.HTTP_UNAUTHORIZED ){
                Logger.i("token error, clearing user data")
            }

            response
        }
    }


    @AppScope
    @Provides
    fun loggingInterceptor(): LoggingInterceptor {
        return LoggingInterceptor.Builder()
            .loggable(BuildConfig.SHOW_LOG)
            .setLevel(Level.BODY)
            .log(Platform.INFO)
            .request("Request")
            .response("Response")
            .build()
    }

    @AppScope
    @Provides
    fun okHttpClient(interceptor: Interceptor, httpLoggingInterceptor: LoggingInterceptor, context: Context): OkHttpClient {
        return OkHttpClient().newBuilder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addNetworkInterceptor(interceptor)
            .build()
    }


}