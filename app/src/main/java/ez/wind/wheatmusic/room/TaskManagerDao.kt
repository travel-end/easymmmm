package ez.wind.wheatmusic.room

import androidx.room.Dao
import androidx.room.Query
import ez.wind.wheatmusic.model.TasksManager

/**
 * @By Journey 2020/11/25
 * @Description
 */
@Dao
interface TaskManagerDao {
    @Query("SELECT * FROM t_task_manager WHERE finish=1 ORDER BY id")
    suspend fun findDownloadedMusic():List<TasksManager>
}