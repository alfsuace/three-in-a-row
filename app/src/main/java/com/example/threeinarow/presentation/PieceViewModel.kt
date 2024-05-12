package com.example.threeinarow.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.threeinarow.domain.GetPiecesUseCase
import com.example.threeinarow.domain.Piece

class PieceViewModel(private val getPiecesUseCase: GetPiecesUseCase) {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun loadPieces() {
        _uiState.postValue(UiState())
        viewModelScope
    }


    data class UiState(
        val pieces: List<Piece> = emptyList()
    )
}