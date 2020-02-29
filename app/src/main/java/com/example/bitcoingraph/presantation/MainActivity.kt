package com.example.bitcoingraph.presantation

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import com.example.bitcoingraph.R
import com.example.bitcoingraph.di.ActivityComponent
import com.example.bitcoingraph.interactor.BitcoinGraphFailure
import com.example.bitcoingraph.interactor.BitcoinGraphState
import com.example.bitcoingraph.interactor.BitcoinGraphSuccess
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModel: BitcoinGraphViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        run {
            viewModel
                .fetchBitcoinData()
                .subscribe(::renderState).also {
                    compositeDisposable.add(it)
                }
        }
        setupChartLayout()
    }

    private fun renderState(state: BitcoinGraphState) {
        when (state) {
            is BitcoinGraphSuccess -> renderSuccess(state)
            is BitcoinGraphFailure -> Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun renderSuccess(state: BitcoinGraphSuccess) {
        val chartPrice = state.data.values.map { it.y }.last().toString()
        txtChartTitle.text = state.data.name
        txtChartDesc.text = state.data.description
        txtChartPrice.text = currencyFormat(chartPrice)

        val yValues = state.data.values
            .asSequence()
            .map { Entry(it.x, it.y) }
            .toList()

        // create a dataset and give it a type
        val set1 = LineDataSet(yValues, "").apply {
            axisDependency = YAxis.AxisDependency.LEFT
            color = Color.rgb(21, 101, 192)
            setDrawValues(false)
            lineWidth = 1.3f
            circleRadius = 2f
            fillAlpha = 65
            fillColor = Color.rgb(21, 101, 192)
            setDrawCircleHole(true)
            setCircleColor(Color.rgb(21, 101, 192))
        }
        // create a data object with the data sets
        val data = LineData(set1)
        data.isHighlightEnabled = false

        // set data
        lineChart.data = data
        lineChart.invalidate()
    }

    private fun setupChartLayout() {

        lineChart.apply {
            description.isEnabled = false
            axisRight.isEnabled = false
            xAxis.isEnabled = false
            setPinchZoom(false)
            setTouchEnabled(false)
            setDrawBorders(false)
            setDrawGridBackground(false)
            lineChart.isDragEnabled = false
            setBackgroundColor(Color.WHITE)
            animateX(1500)

            axisLeft.apply {
                setDrawZeroLine(false)
                textColor = Color.rgb(21, 101, 192)
                setDrawGridLines(true)
                setDrawAxisLine(false)
                isGranularityEnabled = true
            }

            legend.apply {
                setDrawInside(false)
                isEnabled = false
                verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
                horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
            }
        }
    }

    private fun currencyFormat(amount: String): String {
        val formatter = DecimalFormat("USD ###,###,##0.00")
        return formatter.format(java.lang.Double.parseDouble(amount))
    }

    override fun onInject(component: ActivityComponent) {
        component.injectMainActivity(this)
    }
}
