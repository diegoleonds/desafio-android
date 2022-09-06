package com.picpay.desafio.android.data.database.dao

import androidx.room.*
import com.picpay.desafio.android.data.model.user.LocalUser
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    fun insert(user: LocalUser)

    @Insert
    fun insertAll(users: List<LocalUser>)

    @Delete
    fun delete(user: LocalUser)

    @Update
    fun update(user: LocalUser)

    @Query("SELECT * FROM user")
    fun getAll(): Flow<List<LocalUser>>

    @Query("SELECT * FROM user WHERE id = :id")
    fun getById(id: Long): Flow<List<LocalUser>>
}