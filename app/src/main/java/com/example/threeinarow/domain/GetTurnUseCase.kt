package com.example.threeinarow.domain

class GetTurnUseCase(private val boardRepository: BoardRepository) {

    operator fun invoke(): String {
        return boardRepository.getTurn()
    }
}