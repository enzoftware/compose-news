package com.enzoftware.composenews.di

import android.content.Context
import com.enzoftware.composenews.data.ComposeNewsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    companion object {
        const val CACHE_SIZE: Long = 5 * 1024 * 1024
        const val TIMEOUT: Long = 10
    }

//    @Provides
//    fun provideOkHttpClient(cache: Cache): OkHttpClient {
//        return OkHttpClient().newBuilder()
//            .cache(cache)
//            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
//            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
//            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
//            .build()
//    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory,
    ): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.currentsapi.services/v1/")
        .client(okHttpClient)
        .addConverterFactory(moshiConverterFactory).build()


    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(interceptor)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .followRedirects(true)
            .followSslRedirects(true)
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("Authorization",
                        "eyoQ65uEtolJGPCwJl2wiLfpHSg3_KCCxKJVgyCEqw3mXMxe")
                    .build()
                chain.proceed(newRequest)
            }
        return builder.build()
    }

    @Provides
    fun provideCache(@ApplicationContext context: Context): Cache =
        Cache(context.cacheDir, CACHE_SIZE)

    @Provides
    fun providesMoshiConverter(): MoshiConverterFactory = MoshiConverterFactory.create()

    @Provides
    fun providesComposeNewsService(retrofit: Retrofit): ComposeNewsService =
        retrofit.create(ComposeNewsService::class.java)
}