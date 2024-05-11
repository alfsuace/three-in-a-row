package com.example.threeinarow.domain

data class Piece(
    val horizontal: Int,
    val vertical: Int,
    val colour: String,
    val placed: Boolean
)