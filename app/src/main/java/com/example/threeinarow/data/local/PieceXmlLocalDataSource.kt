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

    fun getPieces(): List<Piece> {
        val pieces = mutableListOf<Piece>()
        sharedPreferences.all.values.forEach { serializedPiece ->
            if (serializedPiece is String) {
                val piece = serializer.fromJson(serializedPiece, Piece::class.java)
                pieces.add(piece)
            }
        }
        return pieces
    }

    fun savePiece(piece: Piece) {
        val pieces = getPieces().toMutableList()
        pieces.add(piece)
        pieces.forEach() {
            sharedPreferences.edit().apply() {
                putString("board", serializer.toJson(it))
            }.apply()
        }
    }

    fun clearBoard() {
        sharedPreferences.edit() {
            clear()
            apply()
        }
    }

}