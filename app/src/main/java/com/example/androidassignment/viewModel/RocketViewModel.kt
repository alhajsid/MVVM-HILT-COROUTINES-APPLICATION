package com.example.androidassignment.viewModel

import androidx.lifecycle.*
import com.app.koltinpoc.utils.DataHandler
import com.example.androidassignment.db.entity.RocketsEntity
import com.example.androidassignment.di.DBRepository
import com.example.androidassignment.di.NetworkRepository
import com.example.androidassignment.di.Transformer
import com.example.androidassignment.model.RocketsModel
import com.example.androidassignment.utils.NetworkConnectivityHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RocketViewModel @Inject constructor(
    private val networkRepository: NetworkRepository,
    private val dbRepository: DBRepository,
    private val networkConnectivityHelper: NetworkConnectivityHelper
) : ViewModel() {

    private val _topHeadlines = MutableLiveData<DataHandler<RocketsModel>>()
    val topHeadlines: LiveData<DataHandler<RocketsModel>> = _topHeadlines

    fun getRockets() {
        _topHeadlines.postValue(DataHandler.LOADING())
        if (networkConnectivityHelper.isNetworkAvailable) {
            viewModelScope.launch(coroutineExceptionHandler) {
                val response = networkRepository.getAllRockets()
                response.body()?.let {
                    dbRepository.insertAllRocket(it)
                }
                _topHeadlines.postValue(handleResponse(response))
            }
        } else {
            loadRocketsIFromDB()
        }
    }

    private fun loadRocketsIFromDB() {
        viewModelScope.launch {
            val response = getOfflineRockets()
            _topHeadlines.postValue(response)
        }
    }

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        println("Handle $exception in CoroutineExceptionHandler")
        loadRocketsIFromDB()
    }

    private fun handleResponse(response: Response<RocketsModel>): DataHandler<RocketsModel> {
        if (response.isSuccessful) {
            response.body()?.let { it ->
                return DataHandler.SUCCESS(it)
            }
        }
        return DataHandler.ERROR(message = response.errorBody().toString())
    }

    suspend fun getOfflineRockets(): DataHandler<RocketsModel> {
        val list:ArrayList<RocketsEntity> = dbRepository.getAllRockets() as ArrayList<RocketsEntity>
        val temp = list.map {
            Transformer.convertRocketEntityToRocketModel(it)
        }
        return if (temp.isNullOrEmpty()) {
            DataHandler.ERROR(null, "LIST IS EMPTY OR NULL")
        } else {
            val rocketsModel = RocketsModel()
            rocketsModel.addAll(temp)
            DataHandler.SUCCESS(rocketsModel)
        }
    }
}