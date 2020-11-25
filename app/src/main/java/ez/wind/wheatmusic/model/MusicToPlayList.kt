package ez.wind.wheatmusic.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @By Journey 2020/11/25
 * @Description
 */
@Entity(tableName = "t_music_to_play_list")
data class MusicToPlayList(
    @PrimaryKey(autoGenerate = true)
    var id: Long? =null,
    @ColumnInfo
    var pid: String? = null,
    @ColumnInfo
    var mid: String? = null,
    @ColumnInfo
    var total: Long? =null,
    @ColumnInfo
    var updateDate: Long? =null,
    @ColumnInfo
    var createDate: Long? =null
)