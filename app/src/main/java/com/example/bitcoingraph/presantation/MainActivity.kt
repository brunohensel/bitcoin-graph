package com.example.bitcoingraph.presantation

import android.os.Bundle
import com.example.bitcoingraph.R
import com.example.bitcoingraph.di.ActivityComponent

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onInject(component: ActivityComponent) {
        component.injectMainActivity(this)
    }
}
