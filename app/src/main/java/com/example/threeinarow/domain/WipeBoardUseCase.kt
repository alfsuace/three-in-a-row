package com.example.threeinarow.domain

class WipeBoardUseCase(private val boardRepository: BoardRepository) {

    operator fun invoke() {
        boardRepository.clearBoard()
    }
}
