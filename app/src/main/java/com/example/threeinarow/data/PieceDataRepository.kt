package com.example.threeinarow.data

import com.example.threeinarow.data.local.PieceXmlLocalDataSource
import com.example.threeinarow.domain.Piece
import com.example.threeinarow.domain.PieceRepository

class PieceDataRepository(
    private val pieceXmlLocalDataSource: PieceXmlLocalDataSource
):PieceRepository {
    override suspend fun obtainBoard(): List<Piece> {
        val pieces = pieceXmlLocalDataSource.getPieces()
        if (pieces.isEmpty()) {
            val example1 = Piece("00", 0, 0, "white")
            val example2 = Piece("01", 0, 1, "white")
            val example3 = Piece("10", 1, 0, "white")
            val example4 = Piece("11", 1, 1, "white")
            val list: List<Piece> = mutableListOf(example1, example2, example3, example4)
            saveBoard(list[0])
            saveBoard(list[1])
            saveBoard(list[2])
            saveBoard(list[3])
            return list
        } else {
            return pieces
        }
    }
    override fun saveBoard(piece: Piece) {
        pieceXmlLocalDataSource.savePiece(piece)
    }

    override fun clearBoard() {
        pieceXmlLocalDataSource.clearBoard()
    }


}