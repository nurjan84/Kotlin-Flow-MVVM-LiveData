package com.citrobyte.mpacker.mvvm.models

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("errorCode") val errorCode: String, //-1000
    @SerializedName("errorDescr") val errorDescr: String //Вам необходимо войти через СМС-авторизацию.
)