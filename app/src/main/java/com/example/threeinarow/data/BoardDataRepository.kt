package com.example.threeinarow.data

import com.example.threeinarow.data.local.BoardXmlLocalDataSource
import com.example.threeinarow.domain.BoardRepository
import com.example.threeinarow.domain.Piece

class BoardDataRepository(
    private val boardXmlLocalDataSource: BoardXmlLocalDataSource
) : BoardRepository {
    override suspend fun getBoard(): List<Piece> {
        val pieces = boardXmlLocalDataSource.getPieces()
        if (pieces.isEmpty()) {
            return createBoard()
        } else {
            return pieces
        }
    }

    override fun saveBoard(piece: Piece) {
        boardXmlLocalDataSource.savePiece(piece)
    }

    override fun clearBoard() {
        boardXmlLocalDataSource.clearBoard()
    }

    override fun getTurn(): String {
        val turn = boardXmlLocalDataSource.getTurn()
        return if (turn == "") {
            "white"
        } else {
            "black"
        }
    }

    override fun changeTurn() {
        boardXmlLocalDataSource.changeTurn()
    }

    private fun createBoard(): List<Piece> {
        val example1 = Piece("0", 0, 0, "empty")
        val example2 = Piece("1", 0, 1, "white")
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
        saveBoard(list[0])
        saveBoard(list[1])
        saveBoard(list[2])
        saveBoard(list[3])
        saveBoard(list[4])
        saveBoard(list[5])
        saveBoard(list[6])
        saveBoard(list[7])
        saveBoard(list[8])
        return list
    }

}