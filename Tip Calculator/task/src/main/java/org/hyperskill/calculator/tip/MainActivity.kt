package org.hyperskill.calculator.tip

import android.os.Bundle
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import java.math.BigDecimal
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {

    lateinit var editText: EditText
    lateinit var seekBar: SeekBar
    lateinit var billValueTv: TextView
    lateinit var tipPercentTv: TextView
    lateinit var tipAmountTv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText = findViewById(R.id.edit_text)
        seekBar = findViewById(R.id.seek_bar)
        tipPercentTv = findViewById(R.id.tip_percent_tv)
        billValueTv = findViewById(R.id.bill_value_tv)
        tipAmountTv = findViewById(R.id.tip_amount_tv)
        editText.doAfterTextChanged { text ->
            if (text.toString().isNotEmpty() && text.toString().toBigDecimal() > BigDecimal.ZERO) {
                update()
            } else {
                billValueTv.text = null
                tipPercentTv.text = null
                tipAmountTv.text = null
            }
        }
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                if (editText.text.isNotEmpty()) {
                    update()
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })
    }

    fun update() {
        val bill = BigDecimal(editText.text.toString()).setScale(2, RoundingMode.UNNECESSARY)
        val tip = bill * BigDecimal(seekBar.progress) / BigDecimal(100)
        tipPercentTv.text = getString(R.string.tip_percent, seekBar.progress)
        billValueTv.text = getString(R.string.bill_value, bill.toString())
        tipAmountTv.text = getString(R.string.tip_amount, tip.toString())
    }
}