package com.example.bitcoingraph.data

import io.reactivex.Observable
import retrofit2.http.GET

interface BitcoinApi {

    @GET("charts/market-price?timespan=4weeks&format=json")
    fun getBitcoinData(): Observable<BitcoinData>
}