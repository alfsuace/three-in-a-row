package com.example.threeinarow.data

import com.example.threeinarow.data.remote.BoardDbRemoteDataSource
import com.example.threeinarow.domain.BoardRepository
import com.example.threeinarow.domain.Piece

class BoardDataRepository(
    private val boardRemoteDataSource: BoardDbRemoteDataSource
) : BoardRepository {
    override suspend fun getBoard(): List<Piece> {
        val pieces = boardRemoteDataSource.getPieces()
        if (pieces.size < 7) {
            return createBoard()
        } else {
            return pieces
        }
    }

    override suspend fun saveBoard(piece: Piece) {
        boardRemoteDataSource.savePiece(piece)
    }

    override suspend fun clearBoard() {
        boardRemoteDataSource.clearBoard()
    }

    override suspend fun getTurn(): String {
        return boardRemoteDataSource.getTurn()
    }

    override suspend fun changeTurn() {
        boardRemoteDataSource.changeTurn()
    }

    private suspend fun createBoard(): List<Piece> {
        val example1 = Piece("0", 0, 0, "empty")
        val example2 = Piece("1", 0, 1, "empty")
        val example3 = Piece("2", 0, 2, "empty")
        val example4 = Piece("3", 1, 0, "empty")
        val example5 = Piece("4", 1, 1, "empty")
        val example6 = Piece("5", 1, 2, "empty")
        val example7 = Piece("6", 2, 0, "empty")
        val example8 = Piece("7", 2, 1, "empty")
        val example9 = Piece("8", 2, 2, "empty")
        val list: List<Piece> = mutableListOf(
            example1,
            example2,
            example3,
            example4,
            example5,
            example6,
            example7,
            example8,
            example9
        )
        list.forEach(){
            saveBoard(it)
        }
        return list
    }

}