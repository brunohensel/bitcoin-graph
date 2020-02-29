package com.example.bitcoingraph.data

data class BitcoinData(
    val name: String,
    val period: String,
    val description: String,
    val values: List<PointValue>
)

data class PointValue(val x: Float, val y: Float)
