package com.example.threeinarow.data.local

import com.example.threeinarow.domain.Piece

class BoardLocalDataSource(private val pieceDao: PieceDao, private val turnDao: TurnDao) {

    fun getPieces(): List<Piece> {
        return try {
            pieceDao.getPieces().map { it.toDomain() }
        } catch (ex: Exception) {
            emptyList()
        }
    }

    fun savePiece(piece: Piece) {
        pieceDao.savePiece(piece.toEntity())
    }

    fun clearBoard() {
        pieceDao.clearBoard()
    }

    fun getTurn(): String {
        return try {
            val turn = turnDao.getTurn()
            turn.toDomain()
        } catch (ex: Exception) {
            "white"
        }
    }

    fun changeTurn() {
        val turn = getTurn()
        turnDao.clearTurn()
        if (turn == "white") {
            turnDao.saveTurn("black".toEntity())
        } else if (turn == "black") {
            turnDao.saveTurn("white".toEntity())
        }
    }
}