package com.example.threeinarow.domain

class ChangeTurnUseCase(private val boardRepository: BoardRepository) {

    suspend operator fun invoke() {
        boardRepository.changeTurn()
    }
}