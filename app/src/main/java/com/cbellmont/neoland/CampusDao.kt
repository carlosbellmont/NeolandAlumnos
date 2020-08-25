package com.cbellmont.neoland

import androidx.room.*

@Dao
interface CampusDao {

    @Query("SELECT * FROM Campus")
    fun getAll() : List<Campus>
    @Insert
    fun insert(campus : Campus)
    @Insert
    fun insertAll(campus : List<Campus>)
    @Update
    fun update(campus : Campus)
    @Delete
    fun delete(campus: Campus)
}

