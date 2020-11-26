package ez.wind.wheatmusic.utils

import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import android.text.TextUtils
import ez.wind.wheatmusic.constants.Constants
import ez.wind.wheatmusic.model.Music
import java.io.File

/**
 * @By Journey 2020/11/25
 * @Description
 */
object SongLoader {

    fun getAllLocalMusics(context: Context):MutableList<Music> {
        return getSongsForMedia(context, makeSongCursor(context, null, null))
    }

    private fun getSongsForMedia(context: Context, cursor: Cursor?): MutableList<Music> {
        return getSongsForMedia(context, cursor, null);
    }
    private fun getSongsForMedia(context: Context, cursor: Cursor?, folderPath: String?): MutableList<Music> {
        val results = mutableListOf<Music>()
        try {
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    val id = cursor.getLong(0)
                    val title = cursor.getString(1)
                    val artist = cursor.getString(2)
                    val album = cursor.getString(3)
                    val duration = cursor.getInt(4)
                    val trackNumber = cursor.getInt(5)
                    val artistId = cursor.getString(6)
                    val albumId = cursor.getString(7)
                    val path = cursor.getString(8)
                    val coverUri = CoverLoader.getCoverUri(context, albumId)
                    val music = Music()
                    music.type = Constants.LOCAL
                    music.isOnline = false
                    music.mid = id.toString()
                    music.album = album
                    music.albumId = albumId
                    music.artist = if (artist == "<unknown>") "未知歌手" else artist
                    music.artistId = artistId
                    music.uri = path
                    coverUri?.let { music.coverUri = it }
                    music.trackNumber = trackNumber
                    music.duration = duration.toLong()
                    music.title = title
                    music.date = System.currentTimeMillis()
                    //解决获取文件夹内歌曲时，数量不对
                    if (TextUtils.isEmpty(folderPath) || (!TextUtils.isEmpty(folderPath) && File(path).parent == folderPath)) {
                        results.add(music)
                    }
                } while (cursor.moveToNext())
            }
            cursor?.close()
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        return results
    }
    private fun makeSongCursor(context: Context, selection: String?, paramArrayOfString: Array<String>?): Cursor? {
        val songSortOrder = MediaStore.Audio.Media.DEFAULT_SORT_ORDER
        return makeSongCursor(context, selection, paramArrayOfString, songSortOrder)
    }
    private fun makeSongCursor(context: Context, selection: String?, paramArrayOfString: Array<String>?, sortOrder: String?): Cursor? {
        var selectionStatement = "duration>60000 AND is_music=1 AND title != ''"

        if (!TextUtils.isEmpty(selection)) {
            selectionStatement = "$selectionStatement AND $selection"
        }
        return context.contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            arrayOf("_id", "title", "artist", "album", "duration", "track", "artist_id", "album_id", MediaStore.Audio.Media.DATA, "is_music"),
            selectionStatement, paramArrayOfString, sortOrder)
    }
    fun updateMusic(music: Music) {
        DaoLitepal.saveOrUpdateMusic(music)
    }
}