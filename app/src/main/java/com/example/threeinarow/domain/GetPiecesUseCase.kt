package com.example.threeinarow.domain

class GetPiecesUseCase(private val boardRepository: BoardRepository) {

    suspend operator fun invoke(): List<Piece> {
        return boardRepository.getBoard()
    }
}