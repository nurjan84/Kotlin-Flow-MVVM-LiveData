package com.citrobyte.mpacker.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user")
data class User (
    @PrimaryKey
    @SerializedName("userId") var userId: Int?
){
    @SerializedName("firstName") var firstName:String? = null
    @SerializedName("token") var token:String? = null

}