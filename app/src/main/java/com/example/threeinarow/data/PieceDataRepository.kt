package com.example.threeinarow.data

import com.example.threeinarow.data.local.PieceXmlLocalDataSource
import com.example.threeinarow.domain.PieceRepository
import com.example.threeinarow.domain.Piece
import kotlinx.coroutines.GlobalScope

class PieceDataRepository(
    private val pieceXmlLocalDataSource: PieceXmlLocalDataSource
):PieceRepository {
    override suspend fun obtainBoard(): List<Piece> {
        return pieceXmlLocalDataSource.getPiece().apply {

        }
    }
    override fun saveBoard(piece: Piece) {
        pieceXmlLocalDataSource.savePiece(piece)
    }

    override fun clearBoard() {
        pieceXmlLocalDataSource.clearBoard()
    }


}