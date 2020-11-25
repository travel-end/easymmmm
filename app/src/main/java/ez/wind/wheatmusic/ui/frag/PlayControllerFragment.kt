package ez.wind.wheatmusic.ui.frag

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.animation.LinearInterpolator
import ez.wind.wheatmusic.R
import ez.wind.wheatmusic.ui.base.BaseFragment

/**
 * @By Journey 2020/11/25
 * @Description
 */
class PlayControllerFragment:BaseFragment() {
    override fun layoutResId()=R.layout.frag_bottom_play_controller
    override fun initView() {
        super.initView()
    }

    override fun initData() {
        super.initData()
    }

//    private val rotationAnim by lazy {
//        ObjectAnimator.ofFloat(ivSongCover, "rotation", 0.0f, 360.0f).apply {
//            duration = 30000
//            interpolator = LinearInterpolator()
//            repeatCount = ValueAnimator.INFINITE
//            repeatMode = ValueAnimator.RESTART
//        }
//    }

//    fun clearAnim() {
//        if (rotationAnim.isRunning) {
//            rotationAnim.cancel()
//        }
//    }
}