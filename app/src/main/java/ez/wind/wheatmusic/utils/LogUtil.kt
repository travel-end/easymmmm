package ez.wind.wheatmusic.utils

import android.util.Log
import ez.wind.wheatmusic.constants.Constants

/**
 * @By Journey 2020/10/25
 * @Description
 */
object LogUtil {
//    private const val isPrintLog = BuildConfig.LOG_DEBUG
    private const val isPrintLog = true
    private const val  TAG=  Constants.LOG_TAG
    fun i(msg:String) {
        if (isPrintLog) {
            Log.i(TAG,msg)
        }
    }
    fun v(msg:String) {
        if (isPrintLog) {
            Log.v(TAG,msg)
        }
    }
    fun d(msg:String) {
        if (isPrintLog) {
            Log.d(TAG,msg)
        }
    }
    fun w(msg:String) {
        if (isPrintLog) {
            Log.w(TAG,msg)
        }
    }
    fun e(msg:String) {
        if (isPrintLog) {
            Log.e(TAG,msg)
        }
    }
    fun e(tag:String,msg:String) {
        if (isPrintLog) {
            Log.e(tag,msg)
        }
    }
}