package com.arkapp.partyplanner.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arkapp.partyplanner.data.models.UserLogin

/**
 * Created by Abdul Rehman on 17-05-2020.
 * Contact email - abdulrehman0796@gmail.com
 */

@Dao
interface UserLoginDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg user: UserLogin)

    @Query("SELECT * FROM USER_LOGIN WHERE userName = :userName AND password =:password")
    suspend fun getLoggedInUser(userName: String, password: String): List<UserLogin>

}