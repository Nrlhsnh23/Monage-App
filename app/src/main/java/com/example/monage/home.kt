package com.example.monage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.monage.api.monageApi
import com.example.monage.api.retrofitHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class home : AppCompatActivity() {
    lateinit var btnAdd: ImageButton
    lateinit var listTodo: ListView

    val apiKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImljYXBwaW1nY2FzZ3hjeGd0aGJpIiwicm9sZSI6ImFub24iLCJpYXQiOjE2NzA1NzUxOTYsImV4cCI6MTk4NjE1MTE5Nn0.XxDLVw5GRojK4emEVUuTMmJt6RaXQzJoy5DLMoXH7Bw"
    val token = "Bearer $apiKey"

    var items = ArrayList<Model>()
    val monageApi = retrofitHelper.getInstance().create(monageApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        listTodo = findViewById(R.id.list_todo)
        btnAdd = findViewById(R.id.Btn)

        listTodo.setOnItemClickListener { adapterView, view, position, id ->
            val item = adapterView.getItemAtPosition(position) as Model
            val intent = Intent(this, updateTran::class.java)
            intent.putExtra("id", item.Id)
            intent.putExtra("tanggal", item.Tanggal)
            intent.putExtra("label", item.Label)
            intent.putExtra("amount", item.Amount)
            intent.putExtra("description", item.Description)
            startActivity(intent)
        }

        listTodo.setOnItemLongClickListener { adapterView, view, position, id ->
            val item = adapterView.getItemAtPosition(position) as Model

            val builder = AlertDialog.Builder(this)
            builder.setMessage("Are you sure you want to Delete?")
                .setCancelable(false)
                .setPositiveButton("Yes") { dialog, id ->
                    val id = item.Id.toString()
                    var queryId = "eq.$id"
                    deleteItem(queryId)

                    var label = item.Label.toString()
                    Toast.makeText(
                        applicationContext,
                        "Berhasil menghapus todo: $label",
                        Toast.LENGTH_SHORT
                    ).show()

                    getItem()
                }
                .setNegativeButton("No") { dialog, id ->
                    dialog.dismiss()
                }

            val alert = builder.create()
            alert.show()

            return@setOnItemLongClickListener true
        }

        btnAdd.setOnClickListener {
            val intent = Intent(this, addtransaction::class.java)
            startActivity(intent)

        }
    }

    fun setList(Items: ArrayList<Model>) {
        val adapter = listAdapter(this, R.layout.itemlist, Items)
        listTodo.adapter = adapter
    }

    fun deleteItem(id: String) {
        CoroutineScope(Dispatchers.Main).launch {
            monageApi.delete(token = token, apiKey = apiKey, idQuery = id)

        }
    }

    fun getItem() {
        CoroutineScope(Dispatchers.Main).launch {
            val response = monageApi.get(token = token, apiKey = apiKey)

            response.body()?.forEach {
                items.add(
                    Model(
                        Id = it.id,
                        Tanggal = it.tanggal,
                        Label = it.label,
                        Amount = it.amount,
                    )
                )
            }

            setList(items)
        }
    }
        override fun onResume() {
            super.onResume()

            getItem()
        }
    }