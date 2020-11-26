//package ez.wind.wheatmusic.service
//
//import android.annotation.SuppressLint
//import android.app.Service
//import android.content.BroadcastReceiver
//import android.content.Context
//import android.content.Intent
//import android.content.IntentFilter
//import android.os.*
//import android.telephony.PhoneStateListener
//import android.telephony.TelephonyManager
//import androidx.annotation.RequiresApi
//import ez.wind.wheatmusic.constants.Constants
//import ez.wind.wheatmusic.model.Music
//import ez.wind.wheatmusic.utils.LogUtil
//import ez.wind.wheatmusic.utils.PlayHistoryLoader
//import ez.wind.wheatmusic.utils.PlayQueueLoader
//import ez.wind.wheatmusic.utils.SPUtils
//import ez.wind.wheatmusic.utils.player.AudioAndFocusManager
//import ez.wind.wheatmusic.utils.player.IMusicServiceStub
//import ez.wind.wheatmusic.utils.player.MediaSessionManager
//import ez.wind.wheatmusic.utils.player.playqueue.PlayQueueManager
//import ez.wind.wheatmusic.widget.appwidget.StandardWidget
//import java.lang.ref.WeakReference
//
///**
// * @By Journey 2020/11/26
// * @Description
// */
//@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
//class PlayerService:Service() {
//    /**
//     * 错误次数，超过最大错误次数，自动停止播放
//     */
//    private val playErrorTimes =0
//    private val MAX_ERROR_TIMES =1
//    private lateinit var mPlayerEngine:MusicPlayerEngine
//    private lateinit var mWakeLock:PowerManager.WakeLock
//    private var mPlayingMusic: Music?=null
//    private val mPlayQueue = arrayListOf<Music>()
//    private val mHistoryPosition = arrayListOf<Int>()
//    private var mPlayingPosition :Int= -1
//    private val mPlaylistId = Constants.PLAY_LIST_QUEUE
//    private lateinit var mServiceReceiver:ServiceReceiver
//    private lateinit var mHeadsetReceiver: HeadsetReceiver
//    private lateinit var mHeadsetPlugInReceiver: HeadsetPlugInReceiver
//    private lateinit var mStandWidget:StandardWidget
//    private lateinit var  intentFilter:IntentFilter
//    private lateinit var mediaSessionManager:MediaSessionManager
//    private lateinit var audioAndFocusManager:AudioAndFocusManager
//    private val mBindStub = IMusicServiceStub(this)
//    // 主线程
//    private lateinit var mMainHandler:Handler
//    //工作线程handler
//    private lateinit var mHandler:MusicPlayerHandler
//    private lateinit var mWorkThread:HandlerThread
//    private var isMusicPlaying:Boolean = false
//    private var isRunningForeground:Boolean = false
//    private var percent:Int = 0
//    companion object {
//        private var instance:PlayerService?=null
//        fun getInstance():PlayerService? {
//            if (instance == null) {
//                instance = PlayerService()
//            }
//            return instance
//        }
//    }
//    override fun onCreate() {
//        super.onCreate()
//        instance = this
//        initReceiver()
//        initConfig()
//        initTelephony()
//        initNotify()
//        initMediaPlayer()
//    }
//
//    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        return super.onStartCommand(intent, flags, startId)
//    }
//
//    override fun onBind(intent: Intent?): IBinder? {
//        return mBindStub
//    }
//    private fun initReceiver() {
//        intentFilter = IntentFilter(Context.ACTIVITY_SERVICE)
//        mServiceReceiver = ServiceReceiver()
//        mHeadsetReceiver = HeadsetReceiver()
//        mStandWidget = StandardWidget()
//        mHeadsetPlugInReceiver = HeadsetPlugInReceiver()
//        intentFilter.addAction(ServiceConstants.ACTION_NEXT)
//        intentFilter.addAction(ServiceConstants.ACTION_PREV)
//        intentFilter.addAction(ServiceConstants.META_CHANGED)
//        intentFilter.addAction(ServiceConstants.SHUTDOWN)
//        intentFilter.addAction(ServiceConstants.ACTION_PLAY_PAUSE)
//        //注册
//        registerReceiver(mServiceReceiver,intentFilter)
//        registerReceiver(mHeadsetReceiver,intentFilter)
//        registerReceiver(mHeadsetPlugInReceiver,intentFilter)
//        registerReceiver(mStandWidget,intentFilter)
//    }
//
//
//    @SuppressLint("InvalidWakeLockTag")
//    private fun initConfig() {
//        mMainHandler = Handler(Looper.getMainLooper())
//        PlayQueueManager.getPlayModeId()
//        mWorkThread = HandlerThread("MusicPlayerThread")
//        mWorkThread.start()
//        mHandler = MusicPlayerHandler(this,mWorkThread.looper)
//
//        val powerManager = getSystemService(Context.POWER_SERVICE) as PowerManager
//        mWakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"PlayerWakelockTag")
//        mediaSessionManager = MediaSessionManager(mBindStub,this,mMainHandler)
//        audioAndFocusManager = AudioAndFocusManager(this,mHandler)
//    }
//    private fun initTelephony() {
//        // 监听电话状态改变事件
//        val telManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
//        telManager.listen(servicePhoneStateListener,PhoneStateListener.LISTEN_CALL_STATE)
//    }
//    private fun initNotify() {
//
//    }
//
//    private fun initMediaPlayer() {
//        mPlayerEngine = MusicPlayerEngine(this)
//        mPlayerEngine.setHandler(mHandler)
//    }
//
//    class MusicPlayerHandler(content:PlayerService,looper: Looper):Handler(looper) {
//        private var mCurrentVolume:Float = 1.0f
//        private val content:WeakReference<PlayerService> = WeakReference(content)
//        override fun handleMessage(msg: Message) {
//            super.handleMessage(msg)
//            val service = content.get()
//            synchronized(content) {
//                when(msg.what) {
//
//                }
//            }
//        }
//    }
//    class ServiceReceiver:BroadcastReceiver(){
//        override fun onReceive(context: Context?, intent: Intent?) {
//            TODO("Not yet implemented")
//        }
//
//    }
//    class HeadsetReceiver:BroadcastReceiver() {
//        override fun onReceive(context: Context?, intent: Intent?) {
//            TODO("Not yet implemented")
//        }
//
//    }
//    class HeadsetPlugInReceiver:BroadcastReceiver() {
//        override fun onReceive(context: Context?, intent: Intent?) {
//            TODO("Not yet implemented")
//        }
//
//    }
//
//    private val servicePhoneStateListener = object :PhoneStateListener(){
//        override fun onCallStateChanged(state: Int, phoneNumber: String?) {
//            // 接听或者响铃状态
//            if (state==TelephonyManager.CALL_STATE_OFFHOOK || state==TelephonyManager.CALL_STATE_OFFHOOK) {
//                pause()
//            }
//
//        }
//    }
//
//    fun next(isAuto:Boolean) {
//        synchronized(this) {
//            mPlayingPosition = PlayQueueManager.getNextPosition(isAuto,mPlayQueue.size,mPlayingPosition)
//            LogUtil.e("nextPosition:$mPlayingPosition")
//
//        }
//    }
//
//    fun prev() {
//        synchronized(this) {
//            mPlayingPosition = PlayQueueManager.getPreviousPosition(mPlayQueue.size,mPlayingPosition)
//            LogUtil.e("prevPosition:$mPlayingPosition")
//        }
//    }
//
//    fun playCurrentAndNext() {
//        synchronized(this) {
//            if (mPlayingPosition >= mPlayQueue.size || mPlayingPosition < 0) {
//                return
//            }
//            mPlayingMusic = mPlayQueue[mPlayingPosition]
//            // 更新当前歌曲
//            notifyChange(ServiceConstants.META_CHANGED)
//            // TODO: 2020/11/26
//            notifyChange(ServiceConstants.PLAY_STATE_CHANGED)
//            LogUtil.e("playingSongInfo:${mPlayingMusic.toString()}")
//        }
//    }
//
//    fun pause() {
//
//    }
//
//    private fun notifyChange(what:String) {
//        when(what) {
//            ServiceConstants.META_CHANGED->{
//
//            }
//            ServiceConstants.PLAY_STATE_CHANGED->{
//
//            }
//            ServiceConstants.PLAY_QUEUE_CHANGE,ServiceConstants.PLAY_QUEUE_CLEAR->{
//
//            }
//            ServiceConstants.PLAY_STATE_LOADING_CHANGED->{
//
//            }
//        }
//    }
//
//    private fun saveHistory() {
//        PlayHistoryLoader.addSongToHistory(mPlayingMusic)
//    }
//
//    private fun savePlayQueue(full: Boolean) {
//        if (full) {
//            PlayQueueLoader.updateQueue(mPlayQueue)
//        }
//        if (mPlayingMusic != null) {
//            //保存歌曲id
//            SPUtils.saveCurrentSongId(mPlayingMusic?.mid)
//        }
//        //保存歌曲id
//        SPUtils.playPosition = mPlayingPosition
//        //保存歌曲进度
//        SPUtils.savePosition(getCurrentPosition().toLong())
//        LogUtil.e(
//            "PlayService",
//            "save 保存歌曲id=" + mPlayingPosition + " 歌曲进度= " + getCurrentPosition()
//        )
//    }
//
//    private fun getCurrentPosition():Int {
//        return if (mPlayerEngine.isInitialized) {
//            mPlayerEngine.getCurrentPosition()
//        } else {
//            0
//        }
//    }
//
//    val playingMusic get() = mPlayingMusic
//}