package com.example.threeinarow.data.remote

import com.example.threeinarow.domain.Piece
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.tasks.await

class BoardDbRemoteDataSource(private val firebase: FirebaseDatabase) {

    suspend fun getPieces(): List<Piece> {
        val datos = firebase.getReference("pieces").get().await().children.map {
            it.getValue(PieceRemoteModel::class.java)!!.toModel()
        }
        return datos
    }

    suspend fun savePiece(piece: Piece) {
        firebase.getReference("pieces").child(piece.id).setValue(piece.toRemoteModel())
    }

    suspend fun clearBoard() {
        firebase.getReference("pieces").removeValue()
    }

    suspend fun getTurn(): String {
        val response = firebase.getReference("turn").get().await().getValue(TurnRemoteModel::class.java)!!.toModel()
        if (response != "") {
            return response
        } else {
            return "white"
        }
    }

    suspend fun changeTurn() {
        val turn = getTurn()
        if (turn == "white") {
            firebase.getReference("turn").setValue(TurnRemoteModel("1", "black"))
        } else if (turn == "black") {
            firebase.getReference("turn").setValue(TurnRemoteModel("1", "white"))
        }
    }

}