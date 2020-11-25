package ez.wind.wheatmusic.utils

import android.content.Context
import android.text.Editable
import android.text.Html
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import ez.wind.wheatmusic.app.WheatApp
import java.lang.reflect.ParameterizedType

/**
 * @By Journey 2020/11/25
 * @Description
 */
/**
 * 通过反射获取父类泛型<T>对应的Class类
 */
@Suppress("UNCHECKED_CAST")
fun <T> getClass(t: Any): Class<T> =
    (t.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<T>

fun dp2px(context: Context, dp : Float) : Int {
    val scale  = context.resources.displayMetrics.density
    return (dp*scale + 0.5f).toInt()
}

fun getStatusBarHeight(context: Context):Int {
    var height = dp2px(context,20f)
    return try {
        val resId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resId > 0) {
            height = context.resources.getDimensionPixelOffset(resId)
        }
        LogUtil.e("statusBarHeight:$height")
        height
    } catch (e:Exception) {
        e.printStackTrace()
        height
    }
}

fun getScreenWidth(context: Context?):Int {
    if (context == null) {
        return 0
    }
    return context.resources.displayMetrics.widthPixels
}

fun getScreenHeight(context: Context?):Int {
    if (context == null) {
        return 0
    }
    return context.resources.displayMetrics.heightPixels
}

fun Int.inflate(parent: ViewGroup, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(parent.context).inflate(this, parent, attachToRoot)
}

fun Int.getStringRes() =
    WheatApp.getInstance().resources.getString(this)

fun String?.getEditableStr(): Editable {
    val value = this ?: ""
    return SpannableStringBuilder(value)
}

fun TextView?.setDiffColor(appointStr: String?, originalStr: String?) {
    if (this != null) {
        if (appointStr != null && originalStr != null) {
            val ori = originalStr.replace(
                appointStr.toRegex(),
                "<font color='#FF4081'>$appointStr</font>"
            )
            text = Html.fromHtml(ori)
        }
    }
}


/**
 * 获取颜色资源
 */
fun Int.getColorRes() =
    WheatApp.getInstance().resources.getColor(this)

/**
 * 字符串不为null或者空
 */
fun String?.isNotNullOrEmpty(): Boolean = !(this == null || this.trim().isBlank())

/**
 * 列表不为null或者空
 */
fun isNotNullOrEmpty(list: List<Any>?): Boolean {
    return !list.isNullOrEmpty()
}

/**
 * toast
 */
fun CharSequence.toast(duration: Int = Toast.LENGTH_SHORT) {
    if (this.isNotEmpty()) {
        Toast.makeText(WheatApp.getInstance(), this, duration).show()
    }
}