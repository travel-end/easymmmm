package ez.wind.wheatmusic.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gyf.immersionbar.ImmersionBar
import ez.wind.wheatmusic.R

/**
 * @By Journey 2020/11/25
 * @Description
 */
abstract class BaseActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId())
        initStatusBar()
        initView()
        initData()
        initAction()
    }
    abstract fun layoutResId(): Int
    open fun initView() {
    }

    open fun initData() {
    }

    open fun initAction() {
    }

    open fun initStatusBar() {
        /*透明状态栏 全屏 深色字体*/
        ImmersionBar
            .with(this)
            .keyboardEnable(true)
            .statusBarDarkFont(true)
            .navigationBarColor(R.color.grayWhites)
            .init()
    }
}