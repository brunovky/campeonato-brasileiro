package com.brunooliveira.campeonatobrasileiro.app

import android.app.Application
import com.brunooliveira.campeonatobrasileiro.repository.*
import com.brunooliveira.campeonatobrasileiro.viewmodel.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}

val apiModule = module {
    factory { AuthInterceptor() }
    factory { provideClient(get()) }
    factory { providerApi(get()) }
    single { provideRetrofit(get()) }
}

val repositoryModule = module {
    factory { FootballDataRepository(get()) }
}

class Application: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@Application)
            modules(viewModelModule, apiModule, repositoryModule)
        }
    }

}