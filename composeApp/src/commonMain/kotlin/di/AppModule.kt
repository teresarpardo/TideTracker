package di

import TidesViewModel
import data.TidesDataSource
import data.TidesDataSourceImpl
import data.TidesUseCase
import data.TidesUseCaseImpl
import org.koin.dsl.module
import viewModelDefinition

fun appModule() = module {
    single<TidesDataSource> { TidesDataSourceImpl() }
    single<TidesUseCase> { TidesUseCaseImpl(get<TidesDataSource>()) }

    viewModelDefinition { TidesViewModel(get<TidesUseCase>()) }
}
