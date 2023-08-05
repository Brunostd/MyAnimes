package com.deny.myanimes.di

import com.deny.myanimes.data.repository.AnimesRepository
import com.deny.myanimes.data.viewmodels.MyAnimesViewModel
import org.koin.androidx.compose.get
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import java.lang.reflect.Array.get

val animesModule = module {
    singleOf(::AnimesRepository)
    viewModelOf(::MyAnimesViewModel)
}