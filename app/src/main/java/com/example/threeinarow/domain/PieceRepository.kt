package com.example.threeinarow.domain

interface PieceRepository {
    suspend fun obtainBoard():List<Piece>
    fun saveBoard(piece: Piece)
    fun clearBoard()

}