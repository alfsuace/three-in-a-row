package com.example.threeinarow.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.threeinarow.domain.Piece

@Database(entities = [Piece::class], version = 1, exportSchema = false)
abstract class BoardDataBase : RoomDatabase() {

    abstract fun pieceDao(): PieceDao
    abstract fun turnDao(): TurnDao

    companion object {
        @Volatile
        private var INSTANCE: BoardDataBase? = null

        fun getInstance(context: Context): BoardDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BoardDataBase::class.java, "BoardDataBase"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}