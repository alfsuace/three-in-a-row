package com.example.threeinarow.domain

class WipeBoardUseCase(private val boardRepository: BoardRepository) {

    suspend operator fun invoke() {
        boardRepository.clearBoard()
    }
}
