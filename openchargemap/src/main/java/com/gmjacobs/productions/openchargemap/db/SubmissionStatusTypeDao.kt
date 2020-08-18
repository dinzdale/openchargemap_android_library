package com.gmjacobs.productions.openchargemap.db

import androidx.room.*
import com.gmjacobs.productions.openchargemap.model.core.StatusType
import com.gmjacobs.productions.openchargemap.model.core.SubmissionStatusType

@Dao
interface SubmissionStatusTypeDao {
    @Query("delete from submission_status_type")
    suspend fun deletTable()

    @Insert
    suspend fun insertSubmissionStatusTypes(submissionStatusTypes: List<SubmissionStatusType>)

    @Update
    suspend fun updateSubmissionStatusType(submissionStatusType: SubmissionStatusType)

    @Delete
    suspend fun deleteSubmissionStatusType(submissionStatusType: SubmissionStatusType)

    @Query("Select * from submission_status_type")
    suspend fun getSubmissionStatusTypes(): List<SubmissionStatusType>
}