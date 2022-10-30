package com.mehyo.marvelcharacters.di

import com.mehyo.marvelcharacters.network.MarvelAPI
import com.mehyo.marvelcharacters.repository.NetworkRepository
import com.mehyo.marvelcharacters.ui.characters.ListViewModel
import com.mehyo.marvelcharacters.ui.details.DetailsViewModel
import com.mehyo.marvelcharacters.utils.Constants
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Network modules:
 * created network api using retrofit variable.
 * then building retrofit variable.
 * And finally created singleton values
 * to inject in constructors the when needed.
 */

val networkModule = module {
    fun createNetworkApi(retrofit: Retrofit) = retrofit.create(MarvelAPI::class.java)

    fun retrofitBuilder() = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants.BASE_URL)
        .build()

    single { createNetworkApi(retrofit = get()) }
    single { retrofitBuilder() }
}

/**
 * Repository modules:
 * created a singleton value
 * to inject in constructors the when needed.
 */
val repositoryModule = module {
    single { NetworkRepository(api = get()) }
}

/**
 * ViewModel modules:
 * created a singleton value
 * to inject viewModel objects the when needed.
 */
val viewModelModule = module {
    viewModel { ListViewModel(repository = get()) }
    viewModel { DetailsViewModel(repository = get()) }
}