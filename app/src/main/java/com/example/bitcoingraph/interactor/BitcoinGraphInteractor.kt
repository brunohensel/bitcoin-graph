package com.example.bitcoingraph.interactor

import com.example.bitcoingraph.data.BitcoinApi
import com.example.bitcoingraph.data.BitcoinData
import io.reactivex.Observable
import javax.inject.Inject

class BitcoinGraphInteractor @Inject constructor(private val data: BitcoinApi) {

    fun getChartData(): Observable<BitcoinGraphState> =
        data.getBitcoinData()
            .map { BitcoinChartMapper(it) }
            .cast(BitcoinGraphState::class.java)
            .doOnError { it.printStackTrace() }
            .onErrorReturn { BitcoinGraphFailure("Error") }
}

object BitcoinChartMapper {
    operator fun invoke(result: BitcoinData): BitcoinGraphSuccess {
        return BitcoinGraphSuccess(result)

    }
}

sealed class BitcoinGraphState
data class BitcoinGraphSuccess(val data: BitcoinData) : BitcoinGraphState()
data class BitcoinGraphFailure(val message: String) : BitcoinGraphState()