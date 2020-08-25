package com.cbellmont.neoland

import androidx.room.*

@Dao

interface UserDao {
    @Query("SELECT * FROM User")
    fun getAll() : List<User>
    @Insert
    fun insert(user : User)
    @Insert
    fun insertAll(user : List<User>)
    @Update
    fun update(user : User)
    @Delete
    fun delete(user: User)
}
