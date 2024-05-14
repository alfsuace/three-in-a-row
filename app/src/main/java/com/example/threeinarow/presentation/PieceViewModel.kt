package com.example.threeinarow.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.threeinarow.domain.ChangeTurnUseCase
import com.example.threeinarow.domain.GetPiecesUseCase
import com.example.threeinarow.domain.GetTurnUseCase
import com.example.threeinarow.domain.Piece
import com.example.threeinarow.domain.SetPieceUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PieceViewModel(
    private val getPiecesUseCase: GetPiecesUseCase,
    private val setPieceUseCase: SetPieceUseCase,
    private val getTurnUseCase: GetTurnUseCase,
    private val changeTurnUseCase: ChangeTurnUseCase

) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun loadPieces() {
        _uiState.postValue(UiState(pieces = emptyList()))
        viewModelScope.launch(Dispatchers.IO) {
            val response = getPiecesUseCase.invoke()
            _uiState.postValue(UiState(pieces = response))
        }
    }

    fun loadTurn() {
        _uiState.postValue((UiState(turn = "")))
        viewModelScope.launch(Dispatchers.IO) {
            val response = getTurnUseCase.invoke()
            _uiState.postValue(UiState(turn = response))
        }
    }

    fun changeTurn() {
        viewModelScope.launch(Dispatchers.IO) {
            changeTurnUseCase.invoke()
            _uiState.postValue(UiState())
        }
    }

    fun setPiece(piece: Piece) {
        viewModelScope.launch() {
            setPieceUseCase.invoke(piece)
            _uiState.postValue(UiState())
        }
    }

    data class UiState(
        val pieces: List<Piece> = emptyList(),
        val turn: String = ""
    )
}