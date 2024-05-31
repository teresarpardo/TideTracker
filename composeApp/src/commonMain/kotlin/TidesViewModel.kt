import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.TidesUseCaseImpl
import data.model.TidesDomainModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import network.Failure
import network.fold
import utils.State
import utils.ViewState

class TidesViewModel : ViewModel() {

    private val _viewState: MutableStateFlow<TidesViewState> =
        MutableStateFlow(TidesViewState.Initial)
    val viewState: StateFlow<TidesViewState> get() = _viewState

    init {
        getTides()
    }

    private fun getTides() {
        _viewState.value = TidesViewState.Loading
        viewModelScope.launch {
            TidesUseCaseImpl().getTides().fold(
                foldFailure = ::onGetTidesFailure,
                foldSuccess = ::onGetTidesSuccess
            )
        }
    }

    private fun onGetTidesSuccess(response: TidesDomainModel) {
        _viewState.value = TidesViewState.Success(tides = response)
        println(response)
    }

    private fun onGetTidesFailure(error: Failure) {
        _viewState.value = TidesViewState.Error(error.cause)
    }
}

sealed class TidesViewState(
    val tides: TidesDomainModel? = null,
    state: State<Any>
) : ViewState<Any>(state) {

    data object Initial : TidesViewState(state = State.Initial())

    data object Loading : TidesViewState(state = State.Loading())

    class Success(tides: TidesDomainModel) : TidesViewState(tides = tides, state = State.Success())

    class Error(message: String? = null) : TidesViewState(state = State.Error(message))
}
