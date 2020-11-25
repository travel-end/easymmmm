package ez.wind.wheatmusic.ui.frag

import androidx.lifecycle.Observer
import ez.wind.wheatmusic.R
import ez.wind.wheatmusic.ui.base.BaseLifeCycleFragment
import ez.wind.wheatmusic.utils.isNotNullOrEmpty
import ez.wind.wheatmusic.vm.MainFragViewModel
import kotlinx.android.synthetic.main.frag_main.*

/**
 * @By Journey 2020/11/25
 * @Description
 */
class MainFragment :BaseLifeCycleFragment<MainFragViewModel>(){
    override fun layoutResId()=R.layout.frag_main
    override fun initView() {
        super.initView()
    }

    override fun initData() {
        super.initData()
        mViewModel.loadSongs()
    }

    override fun observe() {
        super.observe()
        mViewModel.run {
            mLocalMusics.observe(this@MainFragment,Observer{
                if (isNotNullOrEmpty(it)) {
                    mainTvLocalMusicCount.text = resources.getString(R.string.song_num,it.size)
                }
            })
            mHistoryMusics.observe(this@MainFragment,Observer{
                if (isNotNullOrEmpty(it)) {
                    mainTvLocalMusicCount.text = resources.getString(R.string.song_num,it.size)
                }
            })
            mLovedMusics.observe(this@MainFragment,Observer{
                if (isNotNullOrEmpty(it)) {
                    mainTvLocalMusicCount.text = resources.getString(R.string.song_num,it.size)
                }
            })
            mDownloadedMusics.observe(this@MainFragment,Observer{
                if (isNotNullOrEmpty(it)) {
                    mainTvLocalMusicCount.text = resources.getString(R.string.song_num,it.size)
                }
            })
        }
    }
}