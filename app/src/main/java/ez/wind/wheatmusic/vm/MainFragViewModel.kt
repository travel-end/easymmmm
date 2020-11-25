package ez.wind.wheatmusic.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import ez.wind.wheatmusic.app.WheatApp
import ez.wind.wheatmusic.constants.Constants
import ez.wind.wheatmusic.model.Music
import ez.wind.wheatmusic.room.MusicDatabase
import ez.wind.wheatmusic.utils.SongLoader
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * @By Journey 2020/11/25
 * @Description
 */
class MainFragViewModel:BaseViewModel() {
    val mLocalMusics:MutableLiveData<MutableList<Music>> = MutableLiveData()
    val mHistoryMusics:MutableLiveData<MutableList<Music>> = MutableLiveData()
    val mLovedMusics:MutableLiveData<MutableList<Music>> = MutableLiveData()
    val mDownloadedMusics:MutableLiveData<MutableList<Music>> = MutableLiveData()

    fun loadSongs() {
        val database  = MusicDatabase.getDatabase()
        viewModelScope.launch {
            val localMusicDeferred= async { database.musicDao().findAllLocalMusic() }
            val historyMusicDeferred = async { database.musicToPlayListDao().findPlayListByPid(Constants.PLAY_LIST_HISTORY) }
            val lovedMusicDeferred= async { database.musicDao().findAllLovedMusic() }
            val downloadedMusicDeferred = async { database.taskManagerDao().findDownloadedMusic() }

            mLovedMusics.value = lovedMusicDeferred.await().toMutableList()

            val localMusics = localMusicDeferred.await().toMutableList()
            if (localMusics.isEmpty()){
                localMusics.clear()
                val musicList = SongLoader.getAllLocalMusics(WheatApp.getInstance())
                database.musicDao().addMusicList(musicList)
                localMusics.addAll(musicList)
            }
            mLocalMusics.value = localMusics

            val historyMusics = historyMusicDeferred.await().toMutableList()
            val historyMusic = mutableListOf<Music>()
            for (item in historyMusics) {
                val historyMusicList = database.musicDao().findMusicByMid(item.mid?:"")
                historyMusic.addAll(historyMusicList)
            }
            mHistoryMusics.value = historyMusic

            val downloadedMusics = downloadedMusicDeferred.await().toMutableList()
            val downloadMusic = mutableListOf<Music>()
            downloadedMusics.forEach {tm->
                if (tm.mid != null) {
                    val music = database.musicDao().findMusicByMid(tm.mid!!)[0]
                    downloadMusic.add(music)
                }
            }
            mDownloadedMusics.value = downloadMusic
        }
    }

}