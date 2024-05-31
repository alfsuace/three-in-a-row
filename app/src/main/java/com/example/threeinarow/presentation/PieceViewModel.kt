package com.example.threeinarow.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.threeinarow.domain.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class PieceViewModel(
    private val getPiecesUseCase: GetPiecesUseCase,
    private val setPieceUseCase: SetPieceUseCase,
    private val getTurnUseCase: GetTurnUseCase,
    private val changeTurnUseCase: ChangeTurnUseCase,
    private val wipeBoardUseCase: WipeBoardUseCase,
    private val getWinnerUseCase: GetWinnerUseCase
) : ViewModel() {

    private var currenUiState = UiState()
    private val _uiState = MutableLiveData(currenUiState)
    val uiState: LiveData<UiState> = _uiState

    fun putPiece(piece: Piece) {
        viewModelScope.launch(Dispatchers.IO) {
            val auxPiece = piece.copy(colour = currenUiState.turn)
            setPieceUseCase(auxPiece)
            updateState()
        }
    }


    private fun updateState() {
        viewModelScope.launch(Dispatchers.IO) {
            async { changeTurn() }
            async { loadPieces() }
            async { loadTurn() }
            async { loadWinner() }
        }
    }
    fun changeTurn() {
        viewModelScope.launch(Dispatchers.IO) {
            changeTurnUseCase()
        }
    }

    fun loadPieces() {
        viewModelScope.launch(Dispatchers.IO) {
            val pieces = getPiecesUseCase()
            currenUiState = currenUiState.copy(pieces = pieces)
            _uiState.postValue(currenUiState)
        }
    }

    fun loadTurn() {
        viewModelScope.launch(Dispatchers.IO) {
            val turn = getTurnUseCase()
            currenUiState = currenUiState.copy(turn = turn)
            _uiState.postValue(currenUiState)
        }
    }

    fun loadWinner() {
        viewModelScope.launch(Dispatchers.IO) {
            val winner = getWinnerUseCase()
            currenUiState = currenUiState.copy(winner = winner)
            _uiState.postValue(currenUiState)
        }
    }
    fun wipeBoard() {
        viewModelScope.launch(Dispatchers.IO) {
            wipeBoardUseCase()
            updateState()
        }
    }

    data class UiState(
        val pieces: List<Piece> = emptyList(),
        val turn: String = "",
        val winner: String = ""
    )
}
