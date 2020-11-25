package ez.wind.wheatmusic.ui.base

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import ez.wind.wheatmusic.R
import ez.wind.wheatmusic.utils.getStatusBarHeight

/**
 * @By Journey 2020/11/25
 * @Description
 */
abstract class BaseFragment:Fragment() {
    protected lateinit var mRootView: View
    abstract fun layoutResId():Int
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mRootView = inflater.inflate(layoutResId(),container,false)
        return mRootView
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initBundle()
        initStatusBar()
        initView()
        initData()
        initAction()
    }
    private fun initBundle() {
        arguments?.let {
            getBundle(it)
        }
    }
    open fun getBundle(bundle: Bundle) {
    }
    open fun initView() {
        val titleRootLayout = mRootView.findViewById<LinearLayout>(R.id.main_title_layout)
        if (titleRootLayout!= null) {
            val lp = titleRootLayout.layoutParams as LinearLayout.LayoutParams
            lp.topMargin = getStatusBarHeight(requireContext()) + 10// 补点偏差
        }
    }
    open fun initData() {

    }
    open fun initAction() {

    }
    open fun initStatusBar() {

    }
    /*navigation带动画的切换fragment会导致在切换的时候卡顿，使用这种方法暂时屏蔽这种卡顿。需要寻找其他根本的解决办法*/
    open fun requireLazyInit(millis:Long = 200) {
        lazyHandler.sendEmptyMessageDelayed(0x111,millis)
    }

    private val lazyHandler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if (msg.what == 0x111) {
                lazyInitData()
            }
        }
    }
    open fun lazyInitData() {
    }
    override fun onDestroy() {
        super.onDestroy()
        lazyHandler.removeCallbacksAndMessages(null)
    }
}