package com.example.threeinarow.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TurnDao {
    @Query("SELECT * FROM $TURN_TABLE")
    fun getTurn(): TurnEntity

    @Query("DELETE FROM $TURN_TABLE")
    fun clearTurn()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTurn(turn: TurnEntity)
}