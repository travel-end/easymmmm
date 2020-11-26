package ez.wind.wheatmusic.utils

import ez.wind.wheatmusic.constants.Constants
import ez.wind.wheatmusic.model.Music


/**
 * 作者：yonglong on 2016/11/4 22:30
 */
object PlayHistoryLoader {

    private val TAG = "PlayQueueLoader"

    /**
     * 添加歌曲到播放历史
     */
    fun addSongToHistory(music: Music?) {
        try {
            if (music != null) {
                DaoLitepal.addToPlaylist(music, Constants.PLAY_LIST_HISTORY)
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

    /**
     * 获取播放历史
     */
    fun getPlayHistory(): MutableList<Music> {
        return DaoLitepal.getMusicList(Constants.PLAY_LIST_HISTORY, "updateDate desc")
    }

    /**
     * 清除播放历史
     */
    fun clearPlayHistory() {
        try {
            DaoLitepal.clearPlaylist(Constants.PLAY_LIST_HISTORY)
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }
}
