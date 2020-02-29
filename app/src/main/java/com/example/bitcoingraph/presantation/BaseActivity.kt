package com.example.bitcoingraph.presantation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bitcoingraph.application.BitcoinGraphApplication
import com.example.bitcoingraph.di.ActivityComponent
import com.example.bitcoingraph.di.ActivityModule
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity : AppCompatActivity() {

    val compositeDisposable by lazy { CompositeDisposable() }
    private var component: ActivityComponent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        component = createComponent()
        onInject(this.component!!)
        super.onCreate(savedInstanceState)
    }

    protected abstract fun onInject(component: ActivityComponent)


    private fun createComponent(): ActivityComponent {
        val application = BitcoinGraphApplication::class.java.cast(application)
        val component = application!!.getComponent()
        return component.createMainActivityComponent(ActivityModule(this))
    }

    override fun onStop() {
        compositeDisposable.clear()
        super.onStop()
    }

}