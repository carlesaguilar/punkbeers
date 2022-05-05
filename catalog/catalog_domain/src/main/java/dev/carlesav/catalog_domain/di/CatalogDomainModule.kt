package dev.carlesav.catalog_domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dev.carlesav.catalog_domain.repository.PunkRepository
import dev.carlesav.catalog_domain.use_case.GetBeersUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
object CatalogDomainModule {
    @ViewModelScoped
    @Provides
    fun provideNewsUseCases(repository: PunkRepository): GetBeersUseCaseImpl =
        GetBeersUseCaseImpl(repository = repository)
}