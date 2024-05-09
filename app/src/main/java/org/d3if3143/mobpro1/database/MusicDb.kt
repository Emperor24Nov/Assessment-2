package org.d3if3143.mobpro1.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.d3if3143.mobpro1.model.Music

@Database(entities = [Music::class], version = 1, exportSchema = false)
abstract class MusicDb : RoomDatabase() {

    abstract val dao: MusicDao

    companion object {

        @Volatile
        private var INSTANCE: MusicDb? = null

        fun getinstance(context: Context): MusicDb {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MusicDb::class.java,
                        "music.db"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}