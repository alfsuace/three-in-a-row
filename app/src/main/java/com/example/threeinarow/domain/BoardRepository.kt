package com.example.threeinarow.domain

interface BoardRepository {
    fun obtainBoard():Board
    fun saveBoard()
    fun wipeBoard()

}