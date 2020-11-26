package ez.wind.wheatmusic.constants

/**
 * @By Journey 2020/11/25
 * @Description
 */
object Constants {
    const val LOG_TAG = "WheatMusic"
    // 特殊歌单类型
    const val PLAY_LIST_LOVE = "love"
    const val PLAY_LIST_HISTORY = "history"
    const val PLAY_LIST_LOCAL = "local"
    const val PLAY_LIST_DOWNLOADED = "downloaded"
    const val PLAY_LIST_QUEUE = "queue"
    // 歌曲类型
    const val LOCAL = "local"
    const val QQ = "qq"
    const val XIAMI = "xiami"
    const val BAIDU = "baidu"
    const val NETEASE = "netease"
    const val VIDEO = "video" //本地视频
    const val YOUTUBE = "youtube" //YouTube
    //百度歌单
    const val PLAYLIST_BD_ID = "playlist_bd"
    //网易云歌单
    const val PLAYLIST_WY_ID = "playlist_wy"
    const val PLAYLIST_WY_RECOMMEND_ID = "playlist_wy_recommend_songs"
    //QQ歌单
    const val PLAYLIST_QQ_ID = "playlist_qq"
    //虾米歌单
    const val PLAYLIST_XIA_MI_ID = "playlist_xm"
    //在线歌单
    const val PLAYLIST_CUSTOM_ID = "custom_online"
    const val PLAYLIST_SEARCH_ID = "playlist_search"
    const val PLAYLIST_IMPORT_ID = "playlist_import"
    //百度电台列表
    const val BAIDU_RADIO_LIST = "baidu_radio_list"
    const val NETEASE_ARITIST_LIST = "netease_artist_list"


    /**
     * 搜索过滤
     */
    const val SP_KEY_SEARCH_FILTER_NETEASE = "sp_netease"
    const val SP_KEY_SEARCH_FILTER_QQ = "sp_netease"
    const val SP_KEY_SEARCH_FILTER_XIAMI = "sp_xiami"
    const val SP_KEY_SEARCH_FILTER_BAIDU = "sp_baidu"
    const val SP_KEY_SEARCH_FILTER_ = "sp_netease"
    const val SP_KEY_SONG_QUALITY = "song_quality"

    //歌单操作
    const val PLAYLIST_ADD = 0
    const val PLAYLIST_DELETE = 1
    const val PLAYLIST_UPDATE = 2
    const val PLAYLIST_RENAME = 3

    //QQ APP_ID
    const val APP_ID = "101454823"

    //社区后台操作php
    const val DEFAULT_URL = "http://119.29.27.116/hkmusic/operate.php"
    const val LOGIN_URL = "http://119.29.27.116/hkmusic/login.php"
    const val REGISTER_URL = "http://119.29.27.116/hkmusic/register.php"
    const val UPLOAD_URL = "http://119.29.27.116/hkmusic/upload_file.php"

    //用户邮箱
    const val USER_EMAIL = "email"

    //用户登录密码
    const val PASSWORD = "password"
    const val TOKEN = "token"
    const val TOKEN_TIME = "token_time"
    const val LOGIN_STATUS = "login_status"

    //用户名
    const val USERNAME = "username"

    //性别
    const val USER_SEX = "user_sex"

    //性别
    const val USER_IMG = "user_img"
    const val USER_COLLEGE = "user_college"
    const val USER_MAJOR = "user_major"
    const val USER_CLASS = "user_class"
    const val NICK = "nick"
    const val PHONE = "phone"
    const val SECRET = "secret"

    //悬浮窗屏幕位置
    const val FLOAT_VIEW_X = "float_view_x"
    const val FLOAT_VIEW_Y = "float_view_y"


    //更新用户信息

    //更新用户信息
    const val UPDATE_USER = "updateUserInfo"

    //用户id
    const val USER_ID = "user_id"

    //动态id
    const val SECRET_ID = "secret_id"

    //内容[动态内容|评论内容]
    const val CONTENT = "content"

    //功能
    const val FUNC = "func"

    //摇一摇歌曲
    const val SONG_ADD = "addSong"
    const val SONG = "song"

    //是否是缓存
    const val KEY_IS_CACHE = "is_cache"

    //歌单
    const val PLAYLIST_ID = "playlist"

    const val IS_URL_HEADER = "http"

    const val TEXT_PLAIN = "text/plain"

    /**
     * 悬浮窗权限requestCode
     */
    const val REQUEST_CODE_FLOAT_WINDOW = 0x123
    const val REQUEST_CODE_LOGIN = 10001

    //在线音乐
    const val FILENAME_MP3 = ".mp3"
    const val FILENAME_LRC = ".lrc"
    const val MUSIC_LIST_SIZE = 10

    const val BASE_MUSIC_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?" +
            "from=android&version=5.8.2.0&channel=huwei&operator=1&method=baidu.ting.billboard.billCategory&format=json&kflag=2"

    const val DEAULT_NOTIFICATION = "notification"
    const val TRANSTITION_ALBUM = "transition_album_art"

    /**
     * QQ音乐Api*************************************************
     */
    const val BASE_URL_QQ_MUSIC_URL = "https://u.y.qq.com"

    /**
     * 虾米音乐Api*************************************************
     */
    const val BASE_XIAMI_URL = "http://api.xiami.com/"

    /**
     * 酷狗音乐Api*************************************************
     */
    const val BASE_KUGOU_URL = "http://lyrics.kugou.com/"

    /**
     * 百度音乐Api*************************************************
     */
    const val BASE_URL_BAIDU_MUSIC = "http://musicapi.qianqian.com/"

    const val URL_GET_SONG_INFO = "http://music.baidu.com/data/music/links?songIds="

    /**
     * 在线歌单接口Api*************************************************
     */
    const val BASE_PLAYER_URL = "https://player.zzsun.cc/"

    /**
     * 网易云音乐接口
     */
    const val BASE_NETEASE_URL = "http://musiclake.leanapp.cn"

    /**
     * Bugly app_id
     */
    const val BUG_APP_ID = "fd892b37ea"

    /**
     * 关于的GitHub地址
     */
    const val ABOUT_MUSIC_LAKE = "https://github.com/caiyonglong/MusicLake"
    const val ABOUT_MUSIC_LAKE_ISSUES =
        "https://github.com/caiyonglong/MusicLake/issues/new"
    const val ABOUT_MUSIC_LAKE_PC = "https://github.com/sunzongzheng/music/releases"
    const val ABOUT_MUSIC_LAKE_URL =
        "https://github.com/caiyonglong/MusicLake/blob/develop/README.md"


    /**
     * 当前 DEMO 应用的 APP_KEY，第三方应用应该使用自己的 APP_KEY 替换该 APP_KEY
     */
    const val APP_KEY = "3338754271"

    /**
     * 当前 DEMO 应用的回调页，第三方应用可以使用自己的回调页。
     * 建议使用默认回调页：https://api.weibo.com/oauth2/default.html
     */
    const val REDIRECT_URL = "https://api.weibo.com/oauth2/default.html"

    /**
     * WeiboSDKDemo 应用对应的权限，第三方开发者一般不需要这么多，可直接设置成空即可。
     * 详情请查看 Demo 中对应的注释。
     */
    const val SCOPE = ""

    //    public static final String SOCKET_URL = "http://39.108.214.63:15003";
    const val SOCKET_URL = "https://socket.zzsun.cc"
    const val OAUTH_GITHUB = "oauth_github"
    const val OAUTH_QQ = "oauth_qq"
    const val OAUTH_WEIBO = "oauth_weibo"
    const val GITHUB_BASE_URL = "https://github.com"
    const val GITHUB_REDIRECT_URI = "musiclake://oauth"
    const val GITHUB_CLIENT_ID = "05baa9742e6a72d662a6"
    const val GITHUB_CLIENT_SECRET = "776c29a9eb0822505829483a1dfcd19812ac622f"
    const val GOOGLE_DEVELOPER_KEY = "AIzaSyCVdXoMhAPa1UdAyxKUK5Xp-uxWG612OEg"

}