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

    fun changeTurn() {
        viewModelScope.launch(Dispatchers.IO) {
            changeTurnUseCase()
        }
    }

    private fun updateState() {
        viewModelScope.launch(Dispatchers.IO) {
            async { changeTurn() }
            val piecesToWait = async { getPiecesUseCase() }
            val turnToWait = async { getTurnUseCase() }
            val winnerToWait = async { getWinnerUseCase() }

            val pieces = piecesToWait.await()
            val turn = turnToWait.await()
            val winner = winnerToWait.await()

            currenUiState = UiState(pieces = pieces, turn = turn, winner = winner)
            _uiState.postValue(currenUiState)
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
