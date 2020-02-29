package com.example.bitcoingraph.di

import android.content.Context
import com.example.bitcoingraph.application.BitcoinGraphApplication
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {
    @Provides
    fun provideApplicationContext(app: BitcoinGraphApplication): Context {
        return app.applicationContext
    }
}