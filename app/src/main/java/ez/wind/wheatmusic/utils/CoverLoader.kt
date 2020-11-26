package ez.wind.wheatmusic.utils

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import ez.wind.wheatmusic.model.Music

/**
 * @By Journey 2020/11/25
 * @Description
 */
object CoverLoader {
    fun getCoverUri(context: Context, albumId: String): String? {
        if (albumId == "-1") {
            return null
        }
        var uri: String? = null
        try {
            val cursor = context.contentResolver.query(
                Uri.parse("content://media/external/audio/albums/$albumId"),
                arrayOf("album_art"), null, null, null)
            if (cursor != null) {
                cursor.moveToNext()
                uri = cursor.getString(0)
                cursor.close()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return uri
    }

    fun loadBigImageView(mContext: Context?, music: Music?, callBack: ((Bitmap) -> Unit)?) {
        if (music == null) return
        if (mContext == null) return
//        val url = MusicUtils.getAlbumPic(music.coverUri, music.type, MusicUtils.PIC_SIZE_BIG)
//        GlideApp.with(mContext)
//            .asBitmap()
//            .load(url ?: R.drawable.default_cover_place)
//            .error(coverUriByRandom)
//            .diskCacheStrategy(DiskCacheStrategy.ALL)
//            .into<CustomTarget<Bitmap>>(object : CustomTarget<Bitmap>() {
//                override fun onLoadCleared(placeholder: Drawable?) {
//
//                }
//
//                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
//                    if (callBack != null && resource != null) {
//                        callBack.invoke(resource)
//                    }
//                }
//            })
    }

    fun loadImageViewByMusic(mContext: Context, music: Music?, callBack: ((Bitmap) -> Unit)?) {
        if (music == null) return
        val url = getCoverUriByMusic(music, false)
//        loadBitmap(mContext, url, callBack)
    }
    private fun getCoverUriByMusic(music: Music, isBig: Boolean): String? {
        return if (music.coverBig != null && isBig) {
            music.coverBig
        } else if (music.coverUri != null) {
            music.coverUri
        } else {
            music.coverSmall
        }
    }
}