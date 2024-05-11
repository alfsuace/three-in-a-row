package com.example.threeinarow.data

import com.example.threeinarow.data.local.BoardXmlLocalDataSource
import com.example.threeinarow.domain.Board
import com.example.threeinarow.domain.BoardRepository

class BoardDataRepository(
    private val boardXmlLocalDataSource: BoardXmlLocalDataSource
):BoardRepository {
    override fun obtainBoard(): Board {
        TODO("Not yet implemented")
    }

    override fun saveBoard() {
        TODO("Not yet implemented")
    }

    override fun wipeBoard() {
        TODO("Not yet implemented")
    }
}