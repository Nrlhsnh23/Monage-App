package com.example.monage.api

data class monageData (
    val tanggal: String,
    val label: String,
    val amount: Double,
    val description: String? = null,
    val done_at: String? = null

)