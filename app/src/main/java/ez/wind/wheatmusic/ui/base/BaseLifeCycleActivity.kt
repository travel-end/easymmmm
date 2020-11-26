package ez.wind.wheatmusic.ui.base

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import ez.wind.wheatmusic.utils.getClass
import ez.wind.wheatmusic.vm.base.BaseViewModel
import ez.wind.wheatmusic.vm.BaseViewModelFactory

/**
 * @By Journey 2020/11/25
 * @Description
 */
abstract class BaseLifeCycleActivity<VM : BaseViewModel> : BaseActivity() {
    // 初始化viewModel
    protected lateinit var mViewModel: VM
    override fun onCreate(savedInstanceState: Bundle?) {
        initViewModel()
        super.onCreate(savedInstanceState)
    }
    private fun initViewModel() {
        mViewModel = ViewModelProvider(this, BaseViewModelFactory())[getClass(this)]
//        shareViewModel = ViewModelProvider(this,BaseViewModelFactory())[ShareViewModel::class.java]
    }
}