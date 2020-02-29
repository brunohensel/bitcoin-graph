package com.example.bitcoingraph.di

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.example.bitcoingraph.data.BitcoinApi
import com.example.bitcoingraph.interactor.BitcoinGraphInteractor
import com.example.bitcoingraph.presantation.BitcoinGraphViewModel
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private var activity: AppCompatActivity) {
    @Provides
    internal fun providesContext(): Context {
        return activity
    }

    @Provides
    @ActivityScope
    fun provideViewModel(interactor: BitcoinGraphInteractor) =
        BitcoinGraphViewModel(interactor)

    @Provides
    @ActivityScope
    fun provideInteractor(data: BitcoinApi) =
        BitcoinGraphInteractor(data)
}