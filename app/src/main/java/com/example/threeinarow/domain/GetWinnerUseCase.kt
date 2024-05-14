package com.example.threeinarow.domain

class GetWinnerUseCase(private val repository: BoardRepository) {

    suspend operator fun invoke(): String {
        val board = repository.getBoard()
        var winner = ""

        if (checkHorizontal(board) != "") {
            winner = checkHorizontal(board)
        } else if (checkVertical(board) != "") {
            winner = checkVertical(board)
        } else if (checkDiagonal(board) != "") {
            winner = checkDiagonal(board)
        } else {
            winner = ""
        }
        return winner
    }

    private fun checkHorizontal(board: List<Piece>): String {
        val board = board.sortedBy { it.id }
        return if (board[0].colour == board[1].colour && board[1].colour == board[2].colour) {
            board[0].colour
        } else if (board[3].colour == board[4].colour && board[4].colour == board[5].colour) {
            board[3].colour
        } else if (board[6].colour == board[7].colour && board[7].colour == board[8].colour) {
            board[6].colour
        } else {
            ""
        }
    }

    private fun checkVertical(board: List<Piece>): String {
        val board = board.sortedBy { it.id }
        return if (board[0].colour == board[3].colour && board[3].colour == board[6].colour) {
            board[0].colour
        } else if (board[1].colour == board[4].colour && board[4].colour == board[7].colour) {
            board[1].colour
        } else if (board[2].colour == board[5].colour && board[5].colour == board[8].colour) {
            board[2].colour
        } else {
            ""
        }
    }

    private fun checkDiagonal(board: List<Piece>): String {
        return if (board[0].colour == board[4].colour && board[4].colour == board[8].colour) {
            board[0].colour
        } else if (board[2].colour == board[4].colour && board[4].colour == board[6].colour) {
            board[2].colour
        } else {
            ""
        }
    }
}