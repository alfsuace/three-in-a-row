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

    var currenUiState = UiState()
    private var _uiState = MutableLiveData(currenUiState)
    val uiState: LiveData<UiState> = _uiState

    fun putPiece(piece: Piece){
        _uiState.postValue(currenUiState)
        viewModelScope.launch(Dispatchers.IO) {
            val auxPiece = piece.copy(colour = currenUiState.turn)
            setPieceUseCase(auxPiece)
            changeTurnUseCase.invoke()
            val piecinas = getPiecesUseCase.invoke()
            val turnsito = getTurnUseCase.invoke()
            _uiState.postValue(
                UiState(
                    pieces = piecinas,
                    turn = turnsito
            ))
        }
    }

    fun loadPieces() {
        _uiState.postValue(UiState(pieces = emptyList()))
        viewModelScope.launch(Dispatchers.IO) {
            val response = getPiecesUseCase.invoke()
            currenUiState =
                currenUiState.copy(pieces = response)
            _uiState.postValue(currenUiState)
        }
    }

    fun loadTurn() {
        _uiState.postValue((UiState(turn = "")))
        viewModelScope.launch(Dispatchers.IO) {
            val response = getTurnUseCase.invoke()
            currenUiState =
                currenUiState.copy(turn = response)
            _uiState.postValue(currenUiState)
        }
    }

    fun changeTurn() {
        viewModelScope.launch(Dispatchers.IO) {
            changeTurnUseCase.invoke()
            _uiState.postValue(currenUiState)
        }
    }

    fun setPiece(piece: Piece) {
        viewModelScope.launch() {
            setPieceUseCase.invoke(piece)
            _uiState.postValue(currenUiState)
        }
    }

    data class UiState(
        val pieces: List<Piece> = emptyList(),
        val turn: String = ""
    )
}