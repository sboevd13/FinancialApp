package com.java.financialapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.java.financialapp.data.Offer
import com.java.financialapp.data.OfferRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class OffersUiState(
    val offers: List<Offer> = emptyList(),
    val isLoading: Boolean = false // Добавили состояние загрузки
)

class OffersViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(OffersUiState())
    val uiState: StateFlow<OffersUiState> = _uiState

    init {
        loadOffers()
    }

    private fun loadOffers() {
        // Запускаем асинхронную операцию в жизненном цикле ViewModel
        viewModelScope.launch {
            // 1. Показываем индикатор загрузки
            _uiState.value = OffersUiState(isLoading = true)

            // 2. Получаем данные из сети
            val offers = OfferRepository.getOffers()

            // 3. Обновляем стейт с данными и убираем индикатор загрузки
            _uiState.value = OffersUiState(offers = offers, isLoading = false)
        }
    }
}