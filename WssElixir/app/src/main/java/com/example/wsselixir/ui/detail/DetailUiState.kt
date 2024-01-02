package com.example.wsselixir.ui.detail

sealed class DetailUiState {
    object Init : DetailUiState()
    object Success : DetailUiState()
    data class Error(val message: String) : DetailUiState()
}