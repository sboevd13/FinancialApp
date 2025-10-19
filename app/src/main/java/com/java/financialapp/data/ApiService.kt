package com.java.financialapp.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

// --- САМОЕ ВАЖНОЕ: ВСТАВЬ СЮДА ССЫЛКУ НА ТВОЙ offers.json ИЗ FIREBASE ---
private const val OFFERS_JSON_URL = "https://firebasestorage.googleapis.com/v0/b/financialapp-6f6b5.firebasestorage.app/o/offers.json?alt=media&token=86edee00-84e9-4de5-a81b-cfd0d96f57ac"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl("https://placeholder-base-url.com/") // Этот URL не важен, т.к. мы используем полный
    .build()

interface ApiService {
    @GET
    suspend fun getOffers(@Url url: String = OFFERS_JSON_URL): List<Offer>
}

object OffersApi {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}