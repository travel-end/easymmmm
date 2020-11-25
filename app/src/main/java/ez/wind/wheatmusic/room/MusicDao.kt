package ez.wind.wheatmusic.room

import androidx.room.*
import ez.wind.wheatmusic.model.Music

/**
 * @By Journey 2020/11/25
 * @Description
 */
@Dao
interface MusicDao {
    @Insert(entity = Music::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMusic(music: Music): Long

    @Insert(entity = Music::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMusicList(musics: List<Music>): List<Long>

    @Delete(entity = Music::class)
    suspend fun deleteMusic(music: Music): Int

    @Delete(entity = Music::class)
    suspend fun deleteMusicList(music: List<Music>): Int

    @Query("DELETE FROM t_music")
    suspend fun deleteAllMusic()

    @Query("SELECT * FROM t_music ORDER BY id")
    suspend fun findAllMusic(): List<Music>

    @Query("SELECT * FROM t_music WHERE isOnline=0 ORDER BY id")
    suspend fun findAllLocalMusic(): List<Music>

    @Query("SELECT * FROM t_music WHERE isLove=1 ORDER BY id")
    suspend fun findAllLovedMusic(): List<Music>

    @Query("SELECT * FROM t_music WHERE mid=(:mid) ORDER BY id")
    suspend fun findMusicByMid(mid:String): List<Music>

}