package com.java.financialapp.data

import androidx.annotation.DrawableRes // <-- ДОБАВЬ ЭТОТ ИМПОРТ

data class Offer(
    val id: Int,
    val name: String,
    @DrawableRes val logoUrl: Int, // ИЗМЕНЕНО: Тип поля теперь Int
    val age: String,
    val term: String,
    val rate: String,
    val amount: String,
    val partnerLink: String
)