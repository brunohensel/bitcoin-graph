package com.example.bitcoingraph.di

import com.example.bitcoingraph.MainActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {

    fun injectMainActivity(mainActivity: MainActivity)
}