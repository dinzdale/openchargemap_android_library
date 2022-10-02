package com.gmjacobs.productions.openchargemap.model

import android.app.Application
import androidx.lifecycle.*
import com.gmjacobs.productions.openchargemap.model.geocode.GeocodeForwardResponseItem
import com.gmjacobs.productions.openchargemap.network.GeocodeApi
import kotlinx.coroutines.launch

class GeocodeViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = GeocodeApi(application)

    fun geocodeForward(query: String): LiveData<Result<List<GeocodeForwardResponseItem>>> {
        val result = MutableLiveData<Result<List<GeocodeForwardResponseItem>>>()
        if (query.isNotEmpty()) {
            viewModelScope.launch {
                result.value = repo.geocodeForward(query)
            }
        }
        return result
    }

//    fun reverseGeocode(lat: Float, lon: Float): LiveData<Result<GeocodeResponse?>> {
//        val result = MutableLiveData<Result<GeocodeResponse?>>()
//        viewModelScope.launch {
//            result.value = repo.reverseGeocode(lat, lon)
//        }
//        return result
//    }
}

