package com.example.tiptime

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun calculateTip(view: View) {

        val stringInTextField = binding.costOfServiceEditText.text.toString()

        val cost = stringInTextField.toDoubleOrNull()

        if (cost == null) {

            Toast.makeText(this, "Enter Cost", Toast.LENGTH_SHORT).show()
            binding.tipResult.text = ""
            return
        } else {
            Toast.makeText(this, "Tip Calculated", Toast.LENGTH_SHORT).show()
        }


        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_1 -> 0.20
            R.id.option_2 -> 0.18
            else -> 0.15
        }
        var tip = tipPercentage * cost

        if (binding.roundUpSwitch.isChecked) {
            tip = kotlin.math.ceil(tip)
        }
        NumberFormat.getCurrencyInstance()

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)


    }


}