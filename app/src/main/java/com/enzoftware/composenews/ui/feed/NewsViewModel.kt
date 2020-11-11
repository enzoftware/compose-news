package com.enzoftware.composenews.ui.feed

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enzoftware.composenews.domain.usecase.NewsUseCase
import com.enzoftware.composenews.utils.CoroutinesDispatchers
import com.enzoftware.composenews.utils.Result
import com.enzoftware.composenews.utils.ViewState
import com.enzoftware.composenews.utils.asLiveData
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsViewModel @ViewModelInject constructor(
    private val useCase: NewsUseCase,
    private val dispatchers: CoroutinesDispatchers
) : ViewModel() {

    private val _mutableUIViewState = MutableLiveData<ViewState<Any>>()

    val uiViewStateObservable = _mutableUIViewState.asLiveData()

    init {
        emitUiState(ViewState.Loading)
        viewModelScope.launch(dispatchers.io) {
            val result = useCase.fetchLatestNews()
            withContext(dispatchers.main) {
                if (result is Result.Success) {
                    emitUiState(ViewState.Success(result.data))
                } else {
                    emitUiState(ViewState.Error)
                }
            }
        }

    }


    fun searchNewsByKeyword(keyword: String){
        emitUiState(ViewState.Loading)
        viewModelScope.launch (dispatchers.io){
            val result = useCase.searchNewsByQuery(keyword)
            withContext(dispatchers.main){
                if(result is Result.Success){
                    emitUiState(ViewState.Success(result.data))
                } else {
                    emitUiState(ViewState.Error)
                }
            }
        }
    }

    private fun emitUiState(state: ViewState<Any>) {
        _mutableUIViewState.postValue(state)
    }
}