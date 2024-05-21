package com.example.threeinarow.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val TURN_TABLE = "turn"

@Entity(tableName = TURN_TABLE)
data class TurnEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "turn") val turn: String
)