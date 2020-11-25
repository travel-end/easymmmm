package ez.wind.wheatmusic.room

import androidx.room.Dao
import androidx.room.Query
import ez.wind.wheatmusic.model.MusicToPlayList

/**
 * @By Journey 2020/11/25
 * @Description
 */
@Dao
interface MusicToPlayListDao {

    @Query("SELECT * FROM t_music_to_play_list WHERE pid=(:pid) ORDER BY id DESC")
    suspend fun findPlayListByPid(pid:String):List<MusicToPlayList>
}