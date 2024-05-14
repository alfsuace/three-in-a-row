package com.example.threeinarow.domain

interface BoardRepository {
    suspend fun getBoard(): List<Piece>
    fun saveBoard(piece: Piece)
    fun clearBoard()
    fun getTurn(): String
    fun changeTurn()
}