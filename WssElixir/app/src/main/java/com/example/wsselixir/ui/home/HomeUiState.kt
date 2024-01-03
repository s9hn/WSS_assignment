package com.example.wsselixir.ui.home

sealed class HomeUiState {
    data object Init : HomeUiState()
    data object Success : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}