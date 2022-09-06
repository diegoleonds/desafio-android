package com.picpay.desafio.android.data.database.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.picpay.desafio.android.data.database.AppDatabase
import com.picpay.desafio.android.data.model.user.LocalUser
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException

internal class UserDaoTest {
    private lateinit var dao: UserDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()
        dao = db.userDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun shouldInsertUserAndGetHimByHisId() = runBlocking {
        val user = LocalUser(
            1L,
            "image.com",
            "name",
            "username"
        )
        dao.insert(user)
        val insertedUser = dao.getById(1L).take(1).toList()[0][0]
        assertEquals(user, insertedUser)
    }

    @Test
    @Throws(Exception::class)
    fun shouldInsertUserAndUpdateHim() = runBlocking {
        val user = LocalUser(
            1L,
            "image.com",
            "name",
            "username"
        )
        dao.insert(user)
        val outdatedUser = dao.getById(1L).take(1).toList()[0][0]

        val userToBeUpdated = user.copy(
            name = "new name"
        )
        dao.update(userToBeUpdated)

        val updatedUser = dao.getById(1L).take(1).toList()[0][0]
        assertNotEquals(outdatedUser, updatedUser)
        assertEquals(userToBeUpdated, updatedUser)
    }

    @Test
    @Throws(Exception::class)
    fun shouldInsertUserAndDeleteHim() = runBlocking {
        val user = LocalUser(
            1L,
            "image.com",
            "name",
            "username"
        )
        dao.insertAll(
            listOf(
                user,
                user.copy(id = 2L, name = "name 2")
            )
        )
        dao.delete(user)
        val fetchedUsers = ArrayList<LocalUser>()
        dao.getAll().take(1).toList().forEach {
            it.forEach { user ->
                fetchedUsers.add(user)
            }
        }

        assertTrue(fetchedUsers.size == 1)
        assertNotEquals(fetchedUsers[0], user)
    }

    @Test
    @Throws(Exception::class)
    fun shouldInsertUsersAndGetThem() = runBlocking {
        val users = ArrayList<LocalUser>()
        for (i in 0 until 2) {
            users.add(
                LocalUser(
                    i.toLong(),
                    "image.com",
                    "name",
                    "username"
                )
            )
        }
        dao.insertAll(users)

        val fetchedUsers = ArrayList<LocalUser>()
        dao.getAll().take(1).toList().forEach {
            it.forEach { user ->
                fetchedUsers.add(user)
            }
        }

        assertEquals(users as List<LocalUser>, fetchedUsers)
    }
}































