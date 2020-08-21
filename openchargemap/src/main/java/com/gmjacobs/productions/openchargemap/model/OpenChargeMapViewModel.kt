package com.gmjacobs.productions.openchargemap.model

import android.app.Application
import androidx.lifecycle.*
import com.gmjacobs.productions.openchargemap.model.core.*
import com.gmjacobs.productions.openchargemap.model.poi.PoiItem
import com.gmjacobs.productions.openchargemap.repo.OpenChargeMapRepository
import kotlinx.coroutines.launch
import java.util.*

class OpenChargeMapViewModelFactory(val application: Application, val daysToExpireDB: Int = 10) : ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = modelClass.constructors.first {
        it.parameterTypes.size == 2 && it.parameterTypes[0].name.compareTo(Application::class.java.name) == 0 && it.parameterTypes[1].name.compareTo("int") == 0
    }.newInstance(application, daysToExpireDB) as T

}

class OpenChargeMapViewModel(application: Application, daysToExpireDB: Int) : AndroidViewModel(application) {
    var repo: OpenChargeMapRepository
    val dbIntialized = MutableLiveData<Boolean>()
    val pois = MutableLiveData<Optional<List<PoiItem>>>()
    val chargeTypes = MutableLiveData<Optional<List<ChargerType>>>()
    val connectionTypes = MutableLiveData<Optional<List<ConnectionType>>>()
    val dataProviders = MutableLiveData<Optional<List<DataProvider>>>()
    val countries = MutableLiveData<Optional<List<Country>>>()
    val currentTypes = MutableLiveData<Optional<List<CurrentType>>>()
    val statusTypes = MutableLiveData<Optional<List<StatusType>>>()
    val chargePoint = MutableLiveData<Optional<ChargePoint>>()
    val operators = MutableLiveData<Optional<List<Operator>>>()
    val submissionStatusTypes = MutableLiveData<Optional<List<SubmissionStatusType>>>()
    val usageTypes = MutableLiveData<Optional<List<UsageType>>>()

    init {
        repo = OpenChargeMapRepository(application)
        dbIntialized.value = false
        repo = OpenChargeMapRepository(application)
        pois.value = Optional.empty()
        chargeTypes.value = Optional.empty()
        connectionTypes.value = Optional.empty()
        dataProviders.value = Optional.empty()
        countries.value = Optional.empty()
        currentTypes.value = Optional.empty()
        statusTypes.value = Optional.empty()
        chargeTypes.value = Optional.empty()
        operators.value = Optional.empty()
        submissionStatusTypes.value = Optional.empty()
        usageTypes.value = Optional.empty()

        viewModelScope.launch {
            repo.loadDb(daysToExpireDB)
            dbIntialized.postValue(true)
        }
    }

    fun clearPois() {
        pois.postValue(Optional.empty())
    }

    fun getChargeTypes() {
        viewModelScope.launch {
            chargeTypes.postValue(Optional.of(repo.getChargeTypes()))
        }
    }

    fun getConnectionTypes() {
        viewModelScope.launch {
            connectionTypes.postValue(Optional.of(repo.getConnectionTypes()))
        }
    }

    fun getDataProviders() {
        viewModelScope.launch {
            dataProviders.postValue(Optional.of(repo.getDataProviders()))
        }
    }

    fun getCountries() {
        viewModelScope.launch {
            countries.postValue(Optional.of(repo.getCountries()))
        }
    }

    fun getCountriesByName(vararg countryNames: String) {
        viewModelScope.launch {
            val countryList = arrayListOf<Country>()
            countryNames.forEach {
                repo.getCountryByName(it)?.let {
                    countryList.add(it)
                }
            }
            if (countryList.size > 0) {
                countries.postValue(Optional.of(countryList))
            } else {
                countries.postValue(Optional.empty())
            }
        }
    }

    fun getCurrenTypes() {
        viewModelScope.launch {
            currentTypes.postValue(Optional.of(repo.getCurrentTypes()))
        }
    }

    fun getStatusTypes() {
        viewModelScope.launch {
            statusTypes.postValue(Optional.of(repo.getStatusTypes()))
        }
    }

    fun getChargePoint() {
        viewModelScope.launch {
            chargePoint.postValue(Optional.of(repo.getChargePoint()))
        }
    }

    fun getOperators() {
        viewModelScope.launch {
            operators.postValue(Optional.of(repo.getOperators()))
        }
    }

    fun getSubmissionStatusTypes() {
        viewModelScope.launch {
            submissionStatusTypes.postValue(Optional.of(repo.getSubmissionStatusTypes()))
        }
    }

    fun getUsageTypes() {
        viewModelScope.launch {
            usageTypes.postValue(Optional.of(repo.getUsageTypes()))
        }
    }

    fun getPOIs(lat: Double, lon: Double, radiusInMiles: Int = 100, countryIDs: List<Int> = arrayListOf(2), maxResults: Int = 50) {
        viewModelScope.launch {
            pois.postValue(Optional.of(repo.getPOIs(lat, lon, radiusInMiles, countryIDs, maxResults)))
        }
    }


}