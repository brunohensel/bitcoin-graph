package com.example.bitcoingraph.presantation

import com.example.bitcoingraph.interactor.BitcoinGraphInteractor
import com.example.bitcoingraph.interactor.BitcoinGraphState
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BitcoinGraphViewModel @Inject constructor(private val interactor: BitcoinGraphInteractor) {

    fun fetchBitcoinData(): Observable<BitcoinGraphState> =
        interactor.getChartData()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
}