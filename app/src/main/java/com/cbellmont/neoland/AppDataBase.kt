package com.cbellmont.neoland

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class,Campus::class,Bootcamp::class], version = 1)


abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun campusDao(): CampusDao
    abstract fun bootcampDao(): BootcampDao
}


