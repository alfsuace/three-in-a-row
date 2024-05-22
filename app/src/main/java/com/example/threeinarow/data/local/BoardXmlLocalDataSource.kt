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
            try {
                list.add(serializer.fromJson(value.toString(), Piece::class.java))
            } catch (ex: Exception) {

            }
        }
        return list.toList()
    }


    fun savePiece(piece: Piece) {
        sharedPreferences.edit() {
            putString(piece.id, serializer.toJson(piece))
            apply()
        }
    }

    fun clearBoard() {
        sharedPreferences.edit() {
            clear()
            apply()
        }
    }

    fun getTurn(): String {
        return sharedPreferences.getString("turn", "white")!!
    }

    fun changeTurn() {
        var player = getTurn()
        if (player == "white") {
            player = "black"
        } else if (player == "black") {
            player = "white"
        }
        sharedPreferences.edit() {
            putString("turn", player)
            apply()
        }
    }

}