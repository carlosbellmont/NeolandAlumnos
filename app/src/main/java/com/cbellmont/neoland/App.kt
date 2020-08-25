package com.cbellmont.neoland

import android.app.Application
import androidx.room.Room

class App : Application() {
    companion object {
        private var db : AppDatabase? = null

        fun getDataBase(applicationContext : Application) : AppDatabase {
            db?.let {

                return it
            }
            db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database-name")
            .build()

            return db as AppDatabase
        }
    }
    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database-name")
            .build()
    }
}