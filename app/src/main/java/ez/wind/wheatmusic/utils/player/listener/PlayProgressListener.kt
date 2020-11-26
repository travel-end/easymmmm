package ez.wind.wheatmusic.utils.player.listener

/**
 * @By Journey 2020/11/26
 * @Description
 */
interface PlayProgressListener {
    fun onProgressUpdate(position:Long, duration:Long)
}