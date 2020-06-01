package com.arkapp.partyplanner.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Abdul Rehman on 17-05-2020.
 * Contact email - abdulrehman0796@gmail.com
 */


/**
 * User login SQL table is created using the following class definition
 * This sql table is used for storing login details of user.
 * */
@Entity(tableName = "USER_LOGIN")
data class UserLogin(
    @PrimaryKey(autoGenerate = true)
    val uid: Int?,
    @ColumnInfo(name = "userName")
    val userName: String?,
    @ColumnInfo(name = "password")
    val password: String?
)