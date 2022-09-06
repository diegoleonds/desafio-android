package com.picpay.desafio.android.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.picpay.desafio.android.data.database.dao.UserDao
import com.picpay.desafio.android.data.model.user.LocalUser

@Database(entities = [LocalUser::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        private const val dbName = "database-name"

        fun create(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java, dbName
            ).build()
        }
    }
    abstract fun userDao(): UserDao
}