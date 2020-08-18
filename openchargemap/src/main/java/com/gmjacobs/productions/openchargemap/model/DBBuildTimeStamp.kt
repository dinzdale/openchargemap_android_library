package com.gmjacobs.productions.openchargemap.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "db_build_timestamp")
data class DBBuildTimeStamp(@PrimaryKey @ColumnInfo(name = "timestamp") val timeStampDate: Date)