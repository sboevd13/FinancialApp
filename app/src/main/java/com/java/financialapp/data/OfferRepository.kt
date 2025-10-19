package com.java.financialapp.data

import com.java.financialapp.R // Убедись, что импорт R правильный

object OfferRepository {

    fun getOffers(): List<Offer> {
        return listOf(
            Offer(
                id = 1, name = "ФинМолл", logoUrl = R.drawable.logo_finmall, // <-- ССЫЛКА НА ЛОКАЛЬНУЮ КАРТИНКУ
                age = "от 18 лет", term = "52 недели", rate = "от 0.6%",
                amount = "до 60 000 ₽", partnerLink = "https://gkvgwv.abadit5rckd.com/c/cd529c26a1a2cac2"
            ),
            Offer(
                id = 2, name = "Деньги на дом", logoUrl = R.drawable.logo_denginadom,
                age = "от 18 лет", term = "52 недели", rate = "0,8%",
                amount = "до 100 000 ₽", partnerLink = "https://fstraffic.com/go/denginadom"
            ),
            Offer(
                id = 4, name = "LIME", logoUrl = R.drawable.logo_lime,
                age = "от 19 лет", term = "30 дней", rate = "0,08% - 0,9%",
                amount = "до 30 000 ₽", partnerLink = "https://fstraffic.com/go/bankadeneg"
            ),
            Offer(
                id = 3, name = "Credit7", logoUrl = R.drawable.logo_credit7,
                age = "от 18 лет", term = "180 недель", rate = "0,8%",
                amount = "до 30 000 ₽", partnerLink = "https://fstraffic.com/go/credit7"
            ),

            // ... добавь остальные компании по аналогии ...
        )
    }
}