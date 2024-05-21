package com.example.threeinarow.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PieceDao {

    @Query("SELECT * FROM $PIECES_TABLE")
    fun getPieces(): List<PieceEntity>

    @Insert
    fun savePiece(piece: PieceEntity)

    @Query("DELETE FROM $PIECES_TABLE")
    fun clearBoard()

}
