package com.example.monage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.monage.api.monageApi
import com.example.monage.api.monageData
import com.example.monage.api.retrofitHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class addtransaction : AppCompatActivity() {

    lateinit var btnAdd : ImageButton
    lateinit var etTanggal : EditText
    lateinit var etLabel : EditText
    lateinit var etAmount : EditText
    lateinit var etDescription : EditText
    lateinit var closeBtn : ImageButton

    val apiKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImljYXBwaW1nY2FzZ3hjeGd0aGJpIiwicm9sZSI6ImFub24iLCJpYXQiOjE2NzA1NzUxOTYsImV4cCI6MTk4NjE1MTE5Nn0.XxDLVw5GRojK4emEVUuTMmJt6RaXQzJoy5DLMoXH7Bw"
    val token = "Bearer $apiKey"

    val monageApi = retrofitHelper.getInstance().create(monageApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addtransaction)

        etTanggal = findViewById(R.id.dateInput)
        etLabel = findViewById(R.id.labelInput)
        etAmount = findViewById(R.id.amountInput)
        etDescription = findViewById(R.id.descInput)
        btnAdd = findViewById(R.id.addButton)
        closeBtn = findViewById(R.id.closeBtn)

        btnAdd.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {

                val data = monageData(tanggal = etTanggal.text.toString(), label = etLabel.text.toString(), amount = etAmount.text.toString().toDoubleOrNull()!!, description = etDescription.text.toString())
                val response = monageApi.create(token = token, apiKey = apiKey, monageData = data)

                Toast.makeText(
                    applicationContext,
                    "Berhasil menambah transaksi!",
                    Toast.LENGTH_SHORT
                ).show()

                finish()
            }
        }
        closeBtn.setOnClickListener {
            finish()
        }
    }
}

