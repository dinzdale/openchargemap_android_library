package com.gmjacobs.productions.openchargemap.model

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.gmjacobs.productions.openchargemap.model.geocode.GeocodeForwardResponseItem
import com.gmjacobs.productions.openchargemap.network.GeocodeApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

class GeocodeViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = GeocodeApi(application)

    private val _forwardResponse = MutableLiveData<Result<List<GeocodeForwardResponseItem>>>()
    val forwardResponse = flowOf(_forwardResponse)

    fun geocodeForward(query: String) {
        if (query.isNotEmpty()) {
            viewModelScope.launch {
                Log.d("geocodeForward","before call: query=${query}")
                _forwardResponse.postValue(repo.geocodeForward(query))
            }
        }
    }

//    fun reverseGeocode(lat: Float, lon: Float): LiveData<Result<GeocodeResponse?>> {
//        val result = MutableLiveData<Result<GeocodeResponse?>>()
//        viewModelScope.launch {
//            result.value = repo.reverseGeocode(lat, lon)
//        }
//        return result
//    }
}

