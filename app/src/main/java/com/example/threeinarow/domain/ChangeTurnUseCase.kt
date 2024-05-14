package com.example.threeinarow.domain

class ChangeTurnUseCase(private val boardRepository: BoardRepository) {

    operator fun invoke() {
        boardRepository.changeTurn()
    }
}