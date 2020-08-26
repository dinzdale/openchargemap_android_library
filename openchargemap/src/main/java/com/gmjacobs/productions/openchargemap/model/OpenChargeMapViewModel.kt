package com.gmjacobs.productions.openchargemap.model

import android.app.Application
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import com.gmjacobs.productions.openchargemap.model.core.*
import com.gmjacobs.productions.openchargemap.model.poi.PoiItem
import com.gmjacobs.productions.openchargemap.network.Api
import com.gmjacobs.productions.openchargemap.repo.OpenChargeMapRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import java.util.*

class OpenChargeMapViewModelFactory(val application: Application, val daysToExpireDB: Int = 10) :
    ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = modelClass.constructors.first {
        it.parameterTypes.size == 2 && it.parameterTypes[0].name.compareTo(Application::class.java.name) == 0 && it.parameterTypes[1].name.compareTo(
            "int"
        ) == 0
    }.newInstance(application, daysToExpireDB) as T

}

class OpenChargeMapViewModel(application: Application, daysToExpireDB: Int) :
    AndroidViewModel(application) {
    var repo: OpenChargeMapRepository
    val dbIntialized = MutableLiveData<Boolean>()
    val paramsFetched = MediatorLiveData<Boolean>()
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

    private var paramsFetchedCnt = 0

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
        waitForParamData(chargeTypes as LiveData<Optional<Any>>)
    }

    fun getConnectionTypes() {
        viewModelScope.launch {
            connectionTypes.postValue(Optional.of(repo.getConnectionTypes()))
        }
        waitForParamData(connectionTypes as LiveData<Optional<Any>>)
    }

    fun getConnectionTypesByName(vararg connectionTypeNames: String) {
        viewModelScope.launch {
            val connectionTypesList = arrayListOf<ConnectionType>()
            connectionTypeNames.forEach {
                repo.getConnectionTypeByName(it)?.let {
                    connectionTypesList.add(it)
                }
            }
            if (connectionTypesList.size > 0) {
                connectionTypes.postValue(Optional.of(connectionTypesList))
            } else {
                connectionTypes.postValue(Optional.empty())
            }
        }
        waitForParamData(connectionTypes as LiveData<Optional<Any>>)
    }

    fun getDataProviders() {
        viewModelScope.launch {
            dataProviders.postValue(Optional.of(repo.getDataProviders()))
        }
        waitForParamData(dataProviders as LiveData<Optional<Any>>)
    }

    fun getCountries() {
        viewModelScope.launch {
            countries.postValue(Optional.of(repo.getCountries()))
        }
        waitForParamData(countries as LiveData<Optional<Any>>)
    }

    fun getOperatorsByName(vararg operatorNames: String) {
        viewModelScope.launch {
            val operatorList = arrayListOf<Operator>()
            operatorNames.forEach {
                repo.getOperatorByName(it)?.let {
                    operatorList.add(it)
                }
            }
            if (operatorList.size > 0) {
                operators.postValue(Optional.of(operatorList))
            } else {
                operators.postValue(Optional.empty())
            }
        }
        waitForParamData(operators as LiveData<Optional<Any>>)
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
        waitForParamData(countries as LiveData<Optional<Any>>)
    }


    fun getCurrenTypes() {
        viewModelScope.launch {
            currentTypes.postValue(Optional.of(repo.getCurrentTypes()))
        }
        waitForParamData(currentTypes as LiveData<Optional<Any>>)
    }


    fun getUsageTypesByName(vararg usageTypeNames: String) {
        viewModelScope.launch {
            val usageTypeList = arrayListOf<UsageType>()
            usageTypeNames.forEach {
                repo.getUsageTypesByName(it)?.let {
                    it.forEach {
                        usageTypeList.add(it)
                    }
                }
            }
            if (usageTypeList.size > 0) {
                usageTypes.postValue(Optional.of(usageTypeList))
            } else {
                usageTypes.postValue(Optional.empty())
            }
        }
        waitForParamData(usageTypes as LiveData<Optional<Any>>)
    }

    fun getStatusTypes() {
        viewModelScope.launch {
            statusTypes.postValue(Optional.of(repo.getStatusTypes()))
        }
        waitForParamData(statusTypes as LiveData<Optional<Any>>)
    }

    fun getStatusTypesByName(vararg statusTypeNames: String) {
        viewModelScope.launch {
            val statusTypeList = arrayListOf<StatusType>()
            statusTypeNames.forEach {
                repo.getStatusTypesByName(it)?.let {
                    it.forEach {
                        statusTypeList.add(it)
                    }
                }
            }
            if (statusTypeList.size > 0) {
                statusTypes.postValue(Optional.of(statusTypeList))
            } else {
                statusTypes.postValue(Optional.empty())
            }
        }
        waitForParamData(statusTypes as LiveData<Optional<Any>>)
    }

    fun getChargePoint() {
        viewModelScope.launch {
            chargePoint.postValue(Optional.of(repo.getChargePoint()))
        }
        waitForParamData(chargePoint as LiveData<Optional<Any>>)
    }

    fun getOperators() {
        viewModelScope.launch {
            operators.postValue(Optional.of(repo.getOperators()))
        }
        waitForParamData(operators as LiveData<Optional<Any>>)
    }

    fun getSubmissionStatusTypes() {
        viewModelScope.launch {
            submissionStatusTypes.postValue(Optional.of(repo.getSubmissionStatusTypes()))
        }
        waitForParamData(submissionStatusTypes as LiveData<Optional<Any>>)

    }

    fun getUsageTypes() {
        viewModelScope.launch {
            usageTypes.postValue(Optional.of(repo.getUsageTypes()))
        }
        waitForParamData(usageTypes as LiveData<Optional<Any>>)

    }


    private fun waitForParamData(vararg liveDataList: LiveData<Optional<Any>>) {
        liveDataList.forEach { nxtLiveData ->
            paramsFetched.addSource(nxtLiveData, Observer {
                it.ifPresent {
                    paramsFetched.removeSource(nxtLiveData)
                    if (--paramsFetchedCnt == 0) {
                        paramsFetched.postValue(true)
                    }
                }
            })
            paramsFetchedCnt++
        }
    }

    fun getPOIs(
        lat: Double,
        lon: Double,
        radiusInMiles: Int = 100,
        countryIDs: List<Int> = arrayListOf(2),
        operatorIDs: List<Int>,
        connectionTypeIDs: List<Int>,
        usageTypeIDs: List<Int>,
        statusTypeIDs: List<Int>,
        distanceUnit: Api.DistanceUnit = Api.DistanceUnit.MILES,
        maxResults: Int = 50,
        compact: Boolean = false,
        verbose: Boolean = true
    ) {
        viewModelScope.launch {
            pois.postValue(
                Optional.of(
                    repo.getPOIs(
                        lat,
                        lon,
                        radiusInMiles,
                        countryIDs,
                        operatorIDs,
                        connectionTypeIDs,
                        usageTypeIDs,
                        statusTypeIDs,
                        distanceUnit,
                        maxResults,
                        compact,
                        verbose
                    )
                )
            )
        }
    }


}