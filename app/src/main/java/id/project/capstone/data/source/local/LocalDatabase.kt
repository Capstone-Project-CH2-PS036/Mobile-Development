package id.project.capstone.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [ResultUrineEntity::class],
    version = 1,
    exportSchema = false
)

abstract class LocalDatabase : RoomDatabase() {
    abstract fun resultDao(): ResultDao
    companion object {

        @Volatile
        private var INSTANCE: LocalDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): LocalDatabase{
            if (INSTANCE == null){
                synchronized(LocalDatabase::class.java){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        LocalDatabase::class.java,"favorite_user_db").build()
                }
            }
            return INSTANCE as LocalDatabase
        }
    }


}
