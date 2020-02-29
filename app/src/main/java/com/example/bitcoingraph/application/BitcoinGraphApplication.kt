package com.example.bitcoingraph.application

import android.app.Application
import com.example.bitcoingraph.di.ApplicationComponent
import com.example.bitcoingraph.di.DaggerApplicationComponent

class BitcoinGraphApplication: Application() {
    private var component: ApplicationComponent? = null

    fun getComponent(): ApplicationComponent{

        if (component == null) {
            component = DaggerApplicationComponent.factory().create(this)
        }

        return component as ApplicationComponent
    }
}