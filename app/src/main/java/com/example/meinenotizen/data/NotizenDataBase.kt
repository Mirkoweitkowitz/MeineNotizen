package com.example.meinenotizen.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Notizen::class], version = 1, exportSchema = false)
abstract class NotizenDataBase : RoomDatabase() {

    companion object{

        private var notizenDataBase: NotizenDataBase? = null

        @Synchronized
        fun getDataBase(context:Context):NotizenDataBase {

            if(notizenDataBase == null) {
                notizenDataBase = Room.databaseBuilder(context,NotizenDataBase::class.java,"notizen.db").build()
            }
            return notizenDataBase!!
        }
    }

    abstract fun notizenDao(): NotizenDao
}

//      abstract val notizenDao: NotizenDao
//}
//private lateinit var INSTANCE: NotizenDataBase
//
//// if there's no Database a new one is built
//
//fun getDataBase(context: Context): NotizenDataBase {
//    synchronized(NotizenDataBase::class.java) {
//        if (!::INSTANCE.isInitialized) {
//            INSTANCE = Room.databaseBuilder(
//                context.applicationContext,
//                NotizenDataBase::class.java,
//                "notizyApp_database"
//            )
//                .build()
//        }
//        return INSTANCE
//    }
//
//
//}