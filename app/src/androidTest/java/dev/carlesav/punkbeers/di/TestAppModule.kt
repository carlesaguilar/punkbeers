package dev.carlesav.punkbeers.di

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import dev.carlesav.catalog_data.di.CatalogDataModule
import dev.carlesav.catalog_domain.repository.PunkRepository
import dev.carlesav.punkbeers.repository.FakeRepositoryImpl
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [CatalogDataModule::class]
)
class TestAppModule {
    @Provides
    @Singleton
    fun providerFakeRepository(): PunkRepository = FakeRepositoryImpl()
}