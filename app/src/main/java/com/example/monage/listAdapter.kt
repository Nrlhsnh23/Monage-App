package com.example.monage

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class listAdapter (var ctx: Context, var resource: Int, var item: ArrayList<Model>): ArrayAdapter<Model>(ctx, resource, item) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater = LayoutInflater.from(ctx)
        val view = layoutInflater.inflate(resource, null)

        val tanggal = view.findViewById<TextView>(R.id.tanggal)
        val label = view.findViewById<TextView>(R.id.title)
        val amount = view.findViewById<TextView>(R.id.amount)

        label.text = item[position].Label
        tanggal.text = item[position].Tanggal
        amount.text = item[position].Amount.toString()

        return view
    }
}