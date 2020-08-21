package com.gmjacobs.productions.openchargemap.utils

import com.gmjacobs.productions.openchargemap.model.core.Country
import java.util.*

fun List<Country>.getCountryIds() : List<Int> {
    val idList = arrayListOf<Int>()
    forEach {
        idList.add(it.iD)
    }
    return idList
}