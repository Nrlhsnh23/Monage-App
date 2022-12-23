package com.example.monage.api

data class monageItem (
        val id: String,
        val tanggal: String,
        val label: String,
        val amount: Double,
        val description: String? = null,
        val done_at: String? = null,
        val created_at: String
    )