package com.example.threeinarow.domain

class SetPieceUseCase(private val boardRepository: BoardRepository) {
    operator fun invoke(piece: Piece) {
        boardRepository.saveBoard(piece)
    }
}