package ez.wind.wheatmusic.event

/**
 * Created by D22434 on 2018/1/10.
 */

data class StatusChangedEvent(var isPrepared: Boolean, var isPlaying: Boolean, var percent: Long = 0)
