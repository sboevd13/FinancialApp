package com.java.financialapp.data

object OfferRepository {
    // Теперь это suspend-функция, так как она выполняет асинхронный сетевой запрос
    suspend fun getOffers(): List<Offer> {
        return try {
            // Вызываем наш сетевой сервис
            OffersApi.retrofitService.getOffers()
        } catch (e: Exception) {
            // Если произошла ошибка (нет интернета и т.д.), выводим ее в лог
            // и возвращаем пустой список, чтобы приложение не упало.
            e.printStackTrace()
            emptyList()
        }
    }
}