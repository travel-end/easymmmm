package ez.wind.wheatmusic.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import ez.wind.wheatmusic.app.WheatApp
import ez.wind.wheatmusic.constants.Constants
import ez.wind.wheatmusic.model.Music
import ez.wind.wheatmusic.utils.SongLoader
import ez.wind.wheatmusic.vm.base.BaseViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * @By Journey 2020/11/25
 * @Description
 */
class MainFragViewModel: BaseViewModel() {
    val mLocalMusics:MutableLiveData<MutableList<Music>> = MutableLiveData()
    val mHistoryMusics:MutableLiveData<MutableList<Music>> = MutableLiveData()
    val mLovedMusics:MutableLiveData<MutableList<Music>> = MutableLiveData()
    val mDownloadedMusics:MutableLiveData<MutableList<Music>> = MutableLiveData()

    fun loadSongs() {

    }

}