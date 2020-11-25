package ez.wind.wheatmusic.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.load.HttpException
import com.google.gson.JsonParseException
import ez.wind.wheatmusic.R
import ez.wind.wheatmusic.utils.getStringRes
import ez.wind.wheatmusic.utils.state.State
import ez.wind.wheatmusic.utils.state.StateType
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import org.apache.http.conn.ConnectTimeoutException
import java.net.ConnectException
import java.net.UnknownHostException

/**
 * @By Journey 2020/11/25
 * @Description
 */
open class BaseViewModel:ViewModel() {
    val loadStatus by lazy {
        MutableLiveData<State>()
    }

    protected fun <T> async(block: suspend () ->T): Deferred<T> {
        return viewModelScope.async { block.invoke() }
    }

    protected fun handleException(e: Throwable, state: State) {
        when (e) {
            is HttpException -> {
                loadStatus.value = state
            }
            is ConnectException -> {
                loadStatus.value = state
            }
            is ConnectTimeoutException -> {
                loadStatus.value = state
            }
            is UnknownHostException -> {
                loadStatus.value = state
            }
            is JsonParseException -> {
                loadStatus.value = state
            }
            is NoClassDefFoundError -> {
                loadStatus.value = state
            }
            else->{
                loadStatus.value = State(StateType.SHOW_TOAST,msg = R.string.unknow_error.getStringRes())
            }
        }
    }
}