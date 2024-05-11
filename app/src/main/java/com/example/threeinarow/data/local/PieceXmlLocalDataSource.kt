package com.example.threeinarow.data.local

import android.content.Context
import androidx.core.content.edit
import com.example.threeinarow.domain.Piece
import com.google.gson.Gson

class PieceXmlLocalDataSource(
    private val context: Context
) {
    private val serializer = Gson()
    private val sharedPreferences = context.getSharedPreferences("game.txt", Context.MODE_PRIVATE)

    fun getPiece(): List<Piece> {
        return sharedPreferences.all.map {
            serializer.fromJson(it.toString(), Piece::class.java)
        }
    }

    fun savePiece(piece: Piece) {
        val serialized = serializer.toJson(piece)
        sharedPreferences.edit() {
            putString("board.txt", serialized)
            apply()
        }
    }

    fun clearBoard() {
        sharedPreferences.edit() {
            clear()
            apply()
        }
    }

}