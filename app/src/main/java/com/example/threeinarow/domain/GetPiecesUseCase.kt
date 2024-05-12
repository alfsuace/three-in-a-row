package com.example.threeinarow.domain

class GetPiecesUseCase(private val pieceRepository: PieceRepository) {

    suspend operator fun invoke(): List<Piece> {
        return pieceRepository.obtainBoard()
    }
}