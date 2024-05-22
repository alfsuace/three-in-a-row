package com.example.threeinarow.data.remote

import com.example.threeinarow.domain.Piece


fun PieceRemoteModel.toModel(): Piece {
    return Piece(
        this.id!!,
        this.horizontal!!,
        this.vertical!!,
        this.colour!!
    )
}

fun Piece.toRemoteModel(): PieceRemoteModel {
    return PieceRemoteModel(
        this.id,
        this.horizontal,
        this.vertical,
        this.colour
    )
}

fun TurnRemoteModel.toModel(): String {
    return this.turn
}

fun String.toRemoteModel(): TurnRemoteModel {
    val idTurn = "1"
    return TurnRemoteModel(
        idTurn,
        this
    )
}