package com.example.investeasy

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import java.text.DecimalFormat
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        val edtcontribution = findViewById<TextInputEditText>(R.id.edt_contribuition)
        val edtnumbers = findViewById<TextInputEditText>(R.id.edt_numbers)
        val edtinterest = findViewById<TextInputEditText>(R.id.edt_interest)


        val tvtotal = findViewById<TextView>(R.id.tv_total)
        val tvincome = findViewById<TextView>(R.id.tv_income)


        val btncalcule = findViewById<Button>(R.id.btn_calcule)
        val btnclean = findViewById<Button>(R.id.btn_clean)

        btnclean.setOnClickListener {
            edtcontribution.text?.clear()
            edtnumbers.text?.clear()
            edtinterest.text?.clear()

        }

        btncalcule.setOnClickListener {

            if (edtcontribution.text.toString() == "" || edtnumbers.text.toString() == "" || edtinterest.text.toString() == ""
            ) {

                Snackbar.make(
                    edtcontribution, "Preencha o campo vazio",
                    Snackbar.LENGTH_LONG
                ).show()
            } else {

                val contribution = edtcontribution.text.toString().toFloat()
                val numbers = edtnumbers.text.toString().toFloat()
                val interest = edtinterest.text.toString().toFloat()


                val percent = (interest / 100f)
                val totalAmount =
                    contribution * ((1 + percent).pow(numbers) - 1) / percent * (1 + percent)
                val earning = totalAmount - (contribution * numbers)

                val decimalFormat = DecimalFormat("#,###.00")

                tvtotal.text = "$${decimalFormat.format(totalAmount)}"
                tvincome.text = "$${decimalFormat.format(earning)}"


            }


        }


    }


}
