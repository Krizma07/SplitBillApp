package com.example.splitbillapp

import com.example.splitbillapp.R
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun calculateTip(view: View) {
        val editTextBillAmount: EditText = findViewById(R.id.editTextBillAmount)
        val editTextNumberOfPeople: EditText = findViewById(R.id.editTextNumberOfPeople)
        val editTextTipPercentage: EditText = findViewById(R.id.editTextTipPercentage)
        val textViewResult: TextView = findViewById(R.id.textViewResult)

        val billAmount = editTextBillAmount.text.toString().toDoubleOrNull() ?: 0.0
        val numberOfPeople = editTextNumberOfPeople.text.toString().toIntOrNull() ?: 1
        val tipPercentage = editTextTipPercentage.text.toString().toDoubleOrNull() ?: 0.0

        if (numberOfPeople <= 0) {
            // Wyświetlenie komunikatu błędu, gdy liczba osób wynosi 0 lub mniej
            Toast.makeText(this, "Liczba osób musi być większa niż 0.", Toast.LENGTH_SHORT).show()
            return
        }

        val totalAmount = calculateTotalAmount(billAmount, tipPercentage)
        val amountPerPerson = totalAmount / numberOfPeople

        val resultText = "Rachunek: $totalAmount\nKwota na osobę: $amountPerPerson"
        textViewResult.text = resultText
    }

    private fun calculateTotalAmount(billAmount: Double, tipPercentage: Double): Double {
        val tipAmount = billAmount * (tipPercentage / 100)
        return billAmount + tipAmount
    }
}
