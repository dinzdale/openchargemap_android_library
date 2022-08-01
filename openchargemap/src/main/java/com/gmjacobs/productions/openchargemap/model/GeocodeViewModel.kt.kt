package com.gmjacobs.productions.openchargemap.model

import android.app.Application
import androidx.lifecycle.*
import com.gmjacobs.productions.openchargemap.model.geocode.GeocodeResponse
import com.gmjacobs.productions.openchargemap.network.GeocodeApi
import kotlinx.coroutines.launch

class GeocodeViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = GeocodeApi(application)

    fun geocode(query: String): LiveData<Result<GeocodeResponse?>> {
        val result = MutableLiveData<Result<GeocodeResponse?>>()
        viewModelScope.launch {
            result.value = repo.geocode(query)
        }
        return result
    }

    fun reverseGeocode(lat: Float, lon: Float): LiveData<Result<GeocodeResponse?>> {
        val result = MutableLiveData<Result<GeocodeResponse?>>()
        viewModelScope.launch {
            result.value = repo.reverseGeocode(lat, lon)
        }
        return result
    }
}

