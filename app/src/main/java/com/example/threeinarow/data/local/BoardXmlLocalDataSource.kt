package com.example.threeinarow.data.local

import android.content.Context
import androidx.core.content.edit
import com.example.threeinarow.domain.Piece
import com.google.gson.Gson

class BoardXmlLocalDataSource(
    private val context: Context
) {
    private val serializer = Gson()
    private val sharedPreferences = context.getSharedPreferences("game.txt", Context.MODE_PRIVATE)

    fun getPieces(): List<Piece> {
        val list = mutableListOf<Piece>()
        sharedPreferences.all.values.forEach { value ->
            val piece = value as? Piece
            if (piece != null) {
                list.add(piece)
            }
        }
        return list.toList()
    }

    fun savePiece(piece: Piece) {
        val pieces = getPieces().toMutableList()
        pieces.add(piece)
        pieces.forEach() {
            sharedPreferences.edit() {
                putString(it.id, serializer.toJson(it))
                apply()
            }
        }
    }

    fun clearBoard() {
        sharedPreferences.edit() {
            clear()
            apply()
        }
    }

    fun getTurn(): String {
        return sharedPreferences.getString("turn", "")!!
    }

    fun changeTurn() {
        var player = getTurn()
        if (player == "white") {
            player = "black"
        } else {
            player = "white"
        }
        sharedPreferences.edit() {
            putString("turn", player)
        }
    }

}