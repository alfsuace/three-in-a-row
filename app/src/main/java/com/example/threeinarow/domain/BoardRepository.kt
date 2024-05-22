package com.example.threeinarow.domain

interface BoardRepository {
    suspend fun getBoard(): List<Piece>
    suspend fun saveBoard(piece: Piece)
    suspend fun clearBoard()
    suspend fun getTurn(): String
    suspend fun changeTurn()
}