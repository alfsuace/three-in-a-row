package com.example.threeinarow.data.local

import com.example.threeinarow.domain.Piece

fun PieceEntity.toDomain(): Piece {
    return Piece(
        this.id,
        this.horizontal,
        this.vertical,
        this.colour
    )
}

fun Piece.toEntity(): PieceEntity {
    return PieceEntity(
        this.id,
        this.horizontal,
        this.vertical,
        this.colour
    )
}

fun TurnEntity.toDomain(): String {
    return this.turn
}

fun String.toEntity(): TurnEntity {
    val idTurn = "turn"
    return TurnEntity(
        idTurn,
        this
    )
}