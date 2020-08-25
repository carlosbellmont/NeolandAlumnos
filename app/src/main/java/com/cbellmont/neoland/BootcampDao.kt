package com.cbellmont.neoland

import androidx.room.*

@Dao
interface BootcampDao {

    @Query("SELECT * FROM Bootcamp")
    fun getAll() : List<Bootcamp>
    @Insert
    fun insert(bootcamp : Bootcamp)
    @Insert
    fun insertAll(bootcamp : List<Bootcamp>)
    @Update
    fun update(bootcamp : Bootcamp)
    @Delete
    fun delete(bootcamp: Bootcamp)
    @Query("SELECT * FROM Bootcamp ORDER BY RANDOM() LIMIT 1")
    fun getRandom():Bootcamp


}