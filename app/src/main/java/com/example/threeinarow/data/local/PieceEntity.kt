package com.example.threeinarow.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val PIECES_TABLE = "pieces"

@Entity(tableName = PIECES_TABLE)
data class PieceEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "horizontal") val horizontal: Int,
    @ColumnInfo(name = "vertical") val vertical: Int,
    @ColumnInfo(name = "colour") val colour: String
)