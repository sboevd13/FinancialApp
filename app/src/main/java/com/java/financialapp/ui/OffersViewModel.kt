package com.java.financialapp.ui


import androidx.lifecycle.ViewModel
import com.java.financialapp.data.Offer
import com.java.financialapp.data.OfferRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class OffersUiState(
    val offers: List<Offer> = emptyList()
)

class OffersViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(OffersUiState())
    val uiState: StateFlow<OffersUiState> = _uiState

    init {
        loadOffers()
    }

    private fun loadOffers() {
        val offers = OfferRepository.getOffers()
        _uiState.value = OffersUiState(offers = offers)
    }
}