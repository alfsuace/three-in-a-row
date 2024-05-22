package com.example.threeinarow.domain

class SetPieceUseCase(private val boardRepository: BoardRepository) {
    suspend operator fun invoke(piece: Piece) {
        boardRepository.saveBoard(piece)
    }
}