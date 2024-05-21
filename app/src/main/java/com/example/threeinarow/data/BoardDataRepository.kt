package com.example.threeinarow.data

import com.example.threeinarow.data.local.BoardLocalDataSource
import com.example.threeinarow.domain.BoardRepository
import com.example.threeinarow.domain.Piece

class BoardDataRepository(
    private val boardLocalDataSource: BoardLocalDataSource
) : BoardRepository {
    override suspend fun getBoard(): List<Piece> {
        val pieces = boardLocalDataSource.getPieces()
        if (pieces.isEmpty()) {
            return createBoard()
        } else {
            return pieces
        }
    }

    override fun saveBoard(piece: Piece) {
        boardLocalDataSource.savePiece(piece)
    }

    override fun clearBoard() {
        boardLocalDataSource.clearBoard()
    }

    override fun getTurn(): String {
        return boardLocalDataSource.getTurn()
    }

    override fun changeTurn() {
        boardLocalDataSource.changeTurn()
    }

    private fun createBoard(): List<Piece> {
        val example1 = Piece("0", 0, 0, "empty")
        val example2 = Piece("1", 0, 1, "empty")
        val example3 = Piece("2", 0, 2, "empty")
        val example4 = Piece("3", 1, 0, "empty")
        val example5 = Piece("4", 1, 1, "empty")
        val example6 = Piece("5", 1, 2, "empty")
        val example7 = Piece("6", 2, 0, "empty")
        val example8 = Piece("7", 2, 1, "empty")
        val example9 = Piece("8", 2, 2, "empty")
        val list: List<Piece> = mutableListOf(
            example1,
            example2,
            example3,
            example4,
            example5,
            example6,
            example7,
            example8,
            example9
        )
        list.forEach(){
            saveBoard(it)
        }
        return list
    }

}