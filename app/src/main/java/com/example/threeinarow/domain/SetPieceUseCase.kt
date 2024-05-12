package com.example.threeinarow.domain

class SetPieceUseCase(private val pieceRepository: PieceRepository) {
    operator fun invoke(piece: Piece) {
        pieceRepository.saveBoard(piece)
    }
}