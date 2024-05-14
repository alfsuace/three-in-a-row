package com.example.threeinarow.domain

interface BoardRepository {
    suspend fun obtainBoard():List<Piece>
    fun saveBoard(piece: Piece)
    fun clearBoard()
    fun getTurn(): String
    fun changeTurn()
}