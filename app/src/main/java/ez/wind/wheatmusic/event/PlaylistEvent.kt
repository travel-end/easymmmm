package ez.wind.wheatmusic.event

import ez.wind.wheatmusic.constants.Constants
import ez.wind.wheatmusic.model.Playlist

/**
 * Author   : D22434
 * version  : 2018/3/1
 * function : 歌单改变
 */
data class PlaylistEvent(var type: String? = Constants.PLAYLIST_CUSTOM_ID, val playlist: Playlist? = null)
