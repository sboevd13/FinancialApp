package com.java.financialapp.data


object OfferRepository {

    fun getOffers(): List<Offer> {
        return listOf(
            Offer(
                id = 1,
                name = "ФинМолл",
                logoUrl = "https://picsum.photos/seed/finmoll/200",
                age = "от 18 лет",
                term = "52 недели",
                rate = "от 0.6%",
                amount = "до 60 000 ₽",
                partnerLink = "https://fstraffic.com/B7x2L"
            ),
            Offer(
                id = 2,
                name = "Деньги на дом",
                logoUrl = "https://picsum.photos/seed/denginadom/200",
                age = "от 18 лет",
                term = "52 недели",
                rate = "0,8%",
                amount = "до 100 000 ₽",
                partnerLink = "https://fstraffic.com/go/denginadom"
            ),
            Offer(
                id = 3,
                name = "Credit7",
                logoUrl = "https://picsum.photos/seed/credit7/200",
                age = "от 18 лет",
                term = "30 дней",
                rate = "0,8%",
                amount = "до 30 000 ₽",
                partnerLink = "https://fstraffic.com/go/credit7"
            ),
            Offer(
                id = 4,
                name = "Банка Денег",
                logoUrl = "https://picsum.photos/seed/bankadeneg/200",
                age = "от 20 лет",
                term = "30 дней",
                rate = "0,8%",
                amount = "до 30 000 ₽",
                partnerLink = "https://fstraffic.com/go/bankadeneg"
            )
        )
    }
}