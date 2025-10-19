package com.java.financialapp.data

// Убираем @DrawableRes, он больше не нужен
data class Offer(
    val id: Int,
    val name: String,
    val logoUrl: String, // ИЗМЕНЕНО: Тип поля снова String
    val age: String,
    val term: String,
    val rate: String,
    val amount: String,
    val partnerLink: String
)