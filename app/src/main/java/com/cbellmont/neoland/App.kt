package com.cbellmont.neoland


import android.app.Application
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.facebook.stetho.Stetho
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class App : Application() {

    companion object {
        private var db : AppDatabase? = null

        fun getDatabase(application: Application): AppDatabase {
            db?.let { return it }

            db = Room.databaseBuilder(application, AppDatabase::class.java, "main.db")
                .addCallback(getCallback())
                .build()
            return db as AppDatabase
        }

        private fun getCallback(): RoomDatabase.Callback {
            return object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    CoroutineScope(Dispatchers.IO).launch {
                        withContext(Dispatchers.IO) {
                            val campusList = listOf(
                                Campus("Barcelona", R.mipmap.neoland_campus_barcelona),
                                Campus("Madrid", R.mipmap.neoland_campus_madrid),
                                Campus("Online", R.mipmap.neoland_campus_online)
                            )

                            App.db?.campusDao()?.insertAll(campusList)
                            val campusListDb = App.db?.campusDao()?.getAll()

                            campusListDb?.let {
                                campusListDb.forEach{Log.e("campusDebug", it.toString())}

                                val bootcampList = mutableListOf(
                                    Bootcamp("Mobile", "Todo sobre Android e iOs", it[0].uId),
                                    Bootcamp("Java", "Todo sobre Java", it[0].uId),
                                    Bootcamp("FullStack", "Todo sobre todo", it[2].uId),
                                    Bootcamp("Java", "Todo sobre Java", it[2].uId),
                                    Bootcamp("Mobile", "Todo sobre Android e iOs", it[1].uId),
                                    Bootcamp("Java", "Todo sobre Java", it[2].uId),
                                    Bootcamp("FullStack", "Todo sobre todo", it[1].uId)
                                )
                                App.db?.bootcampDao()?.insertAll(bootcampList)
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onCreate() {
        super.onCreate()

        var initializerBuilder = Stetho.newInitializerBuilder(this)
        initializerBuilder.enableWebKitInspector(
            Stetho.defaultInspectorModulesProvider(this)
        )
        Stetho.initialize(initializerBuilder.build());
        getDatabase(this)
    }
}
