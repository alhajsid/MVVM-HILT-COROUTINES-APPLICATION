package com.example.androidassignment.viewModel

import androidx.lifecycle.*
import com.app.koltinpoc.utils.DataHandler
import com.example.androidassignment.db.entity.RocketsEntity
import com.example.androidassignment.di.DBRepository
import com.example.androidassignment.di.NetworkRepository
import com.example.androidassignment.di.Transformer
import com.example.androidassignment.model.RocketModel
import com.example.androidassignment.model.RocketsModel
import com.example.androidassignment.utils.NetworkConnectivityHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RocketDetailsViewModel @Inject constructor(
    private val networkRepository: NetworkRepository,
    private val dbRepository: DBRepository,
    private val networkConnectivityHelper: NetworkConnectivityHelper
) : ViewModel() {

    private val _topHeadlines = MutableLiveData<DataHandler<RocketModel>>()
    val topHeadlines: LiveData<DataHandler<RocketModel>> = _topHeadlines

    lateinit var id :String

    fun getRockets(id:String) {
        this.id=id
        _topHeadlines.postValue(DataHandler.LOADING())
        if (networkConnectivityHelper.isNetworkAvailable) {
            viewModelScope.launch(coroutineExceptionHandler) {
                val response = networkRepository.getRocket(id)
                _topHeadlines.postValue(handleResponse(response))
            }
        } else {
            loadRocketsIFromDB(id)
        }
    }

    private fun loadRocketsIFromDB(id:String) {
        viewModelScope.launch {
            val response = getOfflineRocket(id)
            _topHeadlines.postValue(response)
        }
    }

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        println("Handle $exception in CoroutineExceptionHandler")
        loadRocketsIFromDB(id)
    }

    private fun handleResponse(response: Response<RocketModel>): DataHandler<RocketModel> {
        if (response.isSuccessful) {
            response.body()?.let { it ->
                return DataHandler.SUCCESS(it)
            }
        }
        return DataHandler.ERROR(message = response.errorBody().toString())
    }

    suspend fun getOfflineRocket(id:String): DataHandler<RocketModel> {
        val list = dbRepository.getRocket(id)
        return if (list==null) {
            DataHandler.ERROR(null, "LIST IS EMPTY OR NULL")
        } else {
            val temp= Transformer.convertRocketEntityToRocketModel(list)
            DataHandler.SUCCESS(Transformer.convertRocketsModelToRocketModel(temp))
        }
    }
}