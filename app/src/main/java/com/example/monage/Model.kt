package com.example.monage

data class Model(
    val Id: String? = null,
    val Tanggal: String? = null,
    val Label: String? = null,
    val Amount: Double,
    val Description: String? = null,
): java.io.Serializable{}