package com.arkapp.partyplanner.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arkapp.partyplanner.data.models.HistorySummary

/**
 * Created by Abdul Rehman on 17-05-2020.
 * Contact email - abdulrehman0796@gmail.com
 */


/**
 * This class is used to define query for the History Summary table
 * */

@Dao
interface HistorySummaryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg historySummary: HistorySummary)

    @Query("SELECT * FROM HISTORY_SUMMARY WHERE uid = :userUid")
    suspend fun getHistorySummary(userUid: Int): List<HistorySummary>

    @Query("DELETE FROM HISTORY_SUMMARY WHERE id = :id")
    suspend fun delete(id: Int)
}