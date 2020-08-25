package com.gmjacobs.productions.openchargemap.utils

import com.gmjacobs.productions.openchargemap.model.core.*

fun List<Country>.getCountryIds(): List<Int> {
    val idList = arrayListOf<Int>()
    forEach {
        idList.add(it.iD)
    }
    return idList
}

fun List<Operator>.getOperatorIds(): List<Int> {
    val idList = arrayListOf<Int>()
    forEach {
        idList.add(it.iD)
    }
    return idList
}

fun List<ConnectionType>.getConnectionTypeIds(): List<Int> {
    val idList = arrayListOf<Int>()
    forEach {
        idList.add(it.iD)
    }
    return idList
}

fun List<UsageType>.getUsageTypeIds(): List<Int> {
    val idList = arrayListOf<Int>()
    forEach {
        idList.add(it.iD)
    }
    return idList
}

fun List<StatusType>.getStatusTypeIds(): List<Int> {
    val idList = arrayListOf<Int>()
    forEach {
        idList.add(it.iD)
    }
    return idList
}
