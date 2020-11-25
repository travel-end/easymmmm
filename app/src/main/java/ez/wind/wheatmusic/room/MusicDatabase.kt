package ez.wind.wheatmusic.room

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ez.wind.wheatmusic.app.WheatApp
import ez.wind.wheatmusic.model.Music
import ez.wind.wheatmusic.model.MusicToPlayList
import ez.wind.wheatmusic.model.TasksManager

/**
 * @By Journey 2020/11/25
 * @Description
 */
@Database(entities = [Music::class,MusicToPlayList::class,TasksManager::class],version = 1,exportSchema = false)
abstract class MusicDatabase:RoomDatabase() {
    abstract fun musicDao():MusicDao
    abstract fun musicToPlayListDao():MusicToPlayListDao
    abstract fun taskManagerDao():TaskManagerDao
    companion object {
        @Volatile
        private var INSTANCE: MusicDatabase? = null
        fun getDatabase(): MusicDatabase {
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    WheatApp.getInstance().applicationContext,
                    MusicDatabase::class.java,
                    "database_online_song_list"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}