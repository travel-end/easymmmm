package ez.wind.wheatmusic.ui.base

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ez.wind.wheatmusic.utils.getClass
import ez.wind.wheatmusic.utils.state.State
import ez.wind.wheatmusic.utils.state.StateType
import ez.wind.wheatmusic.vm.BaseViewModel
import ez.wind.wheatmusic.vm.BaseViewModelFactory

/**
 * @By Journey 2020/11/25
 * @Description
 */
abstract class BaseLifeCycleFragment<VM : BaseViewModel> : BaseFragment() {
    protected lateinit var mViewModel: VM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }
    private fun initViewModel() {
        mViewModel = ViewModelProvider(this, BaseViewModelFactory())[getClass(this)]
    }

    override fun initData() {
        super.initData()
        observe()
    }

    open fun observe() {
        statusObserve()
    }
    private fun statusObserve() {
        mViewModel.loadStatus.observe(this, loadStatusObserver)
    }

    private val loadStatusObserver by lazy {
        Observer<State> {
            it?.let {
                when (it.code) {
//                    StateType.SUCCESS -> showSuccess()
//                    StateType.ERROR -> showLoadingResultView(it.msg, it.res)
//                    StateType.LOADING_NORMAL -> showLoadingNormal(it.msg)
//                    StateType.LOADING_SONG -> showLoadingSong(it.msg)
//                    StateType.DISMISSING_NORMAL -> dismissLoadingNormal()
//                    StateType.DISMISSING_SONG -> dismissLoadingSong()
//                    StateType.EMPTY -> showLoadingResultView(it.msg, it.res)
//                    StateType.SHOW_TOAST -> showToast(it.msg)
//                    StateType.LOADING_TOP -> showTopLoading()
//                    StateType.DISMISSING_TOP -> dismissTopLoading()
                }
            }
        }
    }
}