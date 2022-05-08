package dev.carlesav.catalog_domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dev.carlesav.catalog_domain.repository.PunkRepository
import dev.carlesav.catalog_domain.use_case.GetBeerDetailUseCase
import dev.carlesav.catalog_domain.use_case.GetBeerDetailUseCaseImpl
import dev.carlesav.catalog_domain.use_case.GetBeersUseCase
import dev.carlesav.catalog_domain.use_case.GetBeersUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
object CatalogDomainModule {
    @ViewModelScoped
    @Provides
    fun provideGetBeersUseCase(repository: PunkRepository): GetBeersUseCase =
        GetBeersUseCaseImpl(repository = repository)

    @ViewModelScoped
    @Provides
    fun provideGetBeerDetailUseCase(repository: PunkRepository): GetBeerDetailUseCase =
        GetBeerDetailUseCaseImpl(repository = repository)
}