package ez.wind.wheatmusic.utils

import ez.wind.wheatmusic.constants.Constants
import ez.wind.wheatmusic.model.Music
import org.jetbrains.anko.doAsync


/**
 * 作者：yonglong on 2016/11/4 22:30
 */

object PlayQueueLoader {

    private val TAG = "PlayQueueLoader"

    /**
     * 获取播放队列
     */
    fun getPlayQueue(): List<Music> {
        return DaoLitepal.getMusicList(Constants.PLAY_LIST_QUEUE)
    }

    /**
     * 添加歌曲到歌单
     */
    fun updateQueue(musics: List<Music>) {
        doAsync {
            clearQueue()
            musics.forEach {
                DaoLitepal.addToPlaylist(it, Constants.PLAY_LIST_QUEUE)
            }
        }
    }

    /**
     * 清空播放列表
     */
    fun clearQueue() {
        try {
            DaoLitepal.clearPlaylist(Constants.PLAY_LIST_QUEUE)
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }
}
