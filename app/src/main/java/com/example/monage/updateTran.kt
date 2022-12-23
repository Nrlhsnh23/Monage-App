package com.example.monage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.monage.api.monageData
import com.example.monage.api.retrofitHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class updateTran : AppCompatActivity() {
    lateinit var btnUpdate: ImageButton
    lateinit var etTanggal : EditText
    lateinit var etLabel : EditText
    lateinit var etAmount : EditText
    lateinit var etDescription : EditText
    lateinit var closeBtn : ImageButton
    lateinit var id : String

    val apiKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImljYXBwaW1nY2FzZ3hjeGd0aGJpIiwicm9sZSI6ImFub24iLCJpYXQiOjE2NzA1NzUxOTYsImV4cCI6MTk4NjE1MTE5Nn0.XxDLVw5GRojK4emEVUuTMmJt6RaXQzJoy5DLMoXH7Bw"
    val token = "Bearer $apiKey"

    val monageApi = retrofitHelper.getInstance().create(com.example.monage.api.monageApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_tran)

        etTanggal = findViewById(R.id.dateInput_u)
        etLabel = findViewById(R.id.labelInput_u)
        etAmount = findViewById(R.id.amountInput_u)
        etDescription = findViewById(R.id.descInput_u)
        btnUpdate = findViewById(R.id.upButton)


        id = intent.getStringExtra("id").toString()

        etTanggal.setText(intent.getStringExtra("tanggal").toString())
        etLabel.setText(intent.getStringExtra("label").toString())
        etAmount.setText(intent.getStringExtra("amount").toString())
        etDescription.setText(intent.getStringExtra("description").toString())

        btnUpdate.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {

                val data = monageData(tanggal = etTanggal.text.toString(), label = etLabel.text.toString(), amount = etAmount.text.toString().toDoubleOrNull()!!, description = etDescription.text.toString())
                val response = monageApi.update(token = token, apiKey = apiKey, idQuery = "eq.$id", monageData = data)

                Toast.makeText(
                    applicationContext,
                    "Berhasil merubah Transaksi!",
                    Toast.LENGTH_SHORT
                ).show()

                finish()
            }
        }
    }
}