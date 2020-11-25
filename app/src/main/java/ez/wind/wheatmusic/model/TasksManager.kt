package ez.wind.wheatmusic.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @By Journey 2020/11/25
 * @Description
 */
@Entity(tableName = "t_task_manager")
data class TasksManager(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    @ColumnInfo
    var tid: Int ? = null,
    @ColumnInfo
    var mid: String? = null,
    @ColumnInfo
    var name: String? = null,
    @ColumnInfo
    var url: String? = null,
    @ColumnInfo
    var path: String? = null,
    @ColumnInfo
    var finish: Boolean = false,
    @ColumnInfo
    var cache: Boolean = true
)