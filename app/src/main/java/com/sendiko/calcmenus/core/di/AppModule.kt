package com.sendiko.calcmenus.core.di

import android.content.Context
import com.sendiko.calcmenus.core.network.ApiService
import com.sendiko.calcmenus.core.preferences.AppPreferences
import com.sendiko.calcmenus.core.utils.dataStore
import com.sendiko.calcmenus.employee.core.EmployeeRepository
import com.sendiko.calcmenus.resto.core.RestoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor { chain ->
            chain.proceed(chain.request().newBuilder().also {
                it.addHeader("Accept", "application/json")
            }.build())
        }.also {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            it.addInterceptor(logging)
        }
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.14:8000/api/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideRestoRepository(apiService: ApiService): RestoRepository{
        return RestoRepository(apiService)
    }

    @Singleton
    @Provides
    fun provideEmployeeRepository(apiService: ApiService): EmployeeRepository{
        return EmployeeRepository(apiService)
    }

    @Singleton
    @Provides
    fun provideAppPreferences(@ApplicationContext context: Context): AppPreferences {
        return AppPreferences(requireNotNull(context.dataStore))
    }
}