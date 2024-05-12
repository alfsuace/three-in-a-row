package com.example.threeinarow.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.threeinarow.domain.GetPiecesUseCase
import com.example.threeinarow.domain.Piece
import kotlinx.coroutines.Dispatchers

class PieceViewModel(private val getPiecesUseCase: GetPiecesUseCase) {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun loadPieces() {
        _uiState.postValue(UiState(pieces = emptyList()))
        viewModelScope.launch(Dispatchers.IO) {
            val response = getPiecesUseCase.invoke()
            _uiState.postValue(UiState(pieces = response))
        }
    }


    data class UiState(
        val pieces: List<Piece> = emptyList()
    )
}