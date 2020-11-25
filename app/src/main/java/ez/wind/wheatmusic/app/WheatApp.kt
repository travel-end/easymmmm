package ez.wind.wheatmusic.app

import androidx.multidex.MultiDexApplication
import com.danikula.videocache.HttpProxyCacheServer
import ez.wind.wheatmusic.utils.FileUtil
import ez.wind.wheatmusic.utils.cache.CacheFileNameGenerator
import java.io.File

/**
 * @By Journey 2020/11/25
 * @Description
 */
class WheatApp: MultiDexApplication() {
    companion object {
        private var instances:WheatApp?=null
        fun getInstance():WheatApp {
            if (instances == null) {
                synchronized(WheatApp::class.java) {
                    if (instances == null) {
                        instances = WheatApp()
                    }
                }
            }
            return instances!!
        }

        fun getProxy(): HttpProxyCacheServer {
            return if (getInstance().proxy == null) {
                getInstance().proxy = getInstance().newProxy()
                getInstance().proxy!!
            } else {
                getInstance().proxy!!
            }
        }
    }
    override fun onCreate() {
        super.onCreate()
        instances= this
    }

    private var proxy: HttpProxyCacheServer?=null

    private fun newProxy(): HttpProxyCacheServer {
        return HttpProxyCacheServer
            .Builder(this)
            .cacheDirectory(File(FileUtil.getMusicCacheDir()))
            .fileNameGenerator(CacheFileNameGenerator())
            .build()
    }
}