package dev.carlesav.catalog_data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.carlesav.catalog_data.remote.PunkApi
import dev.carlesav.catalog_data.repository.PunkRepositoryImpl
import dev.carlesav.catalog_domain.repository.PunkRepository
import dev.carlesav.core.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.sql.Timestamp
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CatalogDataModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor.Level.BODY
                    } else {
                        HttpLoggingInterceptor.Level.NONE
                    }
                })
            .build()
    }

    @Provides
    @Singleton
    fun providePunkApi(client: OkHttpClient): PunkApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create()
    }

    @Provides
    fun provideTimestamp(): Timestamp = Timestamp(System.currentTimeMillis())

    @Provides
    @Singleton
    fun providePunkRepository(
        api: PunkApi,
    ): PunkRepository {
        return PunkRepositoryImpl(
            api = api
        )
    }
}