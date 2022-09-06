package com.picpay.desafio.android.data.model.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.picpay.desafio.android.domain.model.user.User

@Entity(tableName = "user")
data class LocalUser(
    @PrimaryKey override val id: Long,
    @ColumnInfo override val img: String,
    @ColumnInfo override val name: String,
    @ColumnInfo override val username: String
) : User