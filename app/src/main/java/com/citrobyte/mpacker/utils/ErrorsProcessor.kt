package com.citrobyte.mpacker.utils

import android.content.Context
import com.citrobyte.mpacker.R
import com.citrobyte.mpacker.mvvm.models.ErrorResponse
import com.google.gson.Gson
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownServiceException


class ErrorsProcessor constructor( private val context: Context){

    companion object{
        const val TOKEN_ERROR = "-1000"
    }

    fun processError(error:Throwable):Throwable{
        val exception = error.fillInStackTrace()
        return if(exception is HttpException){
            val code = exception.response()?.code()
            val errorBody = exception.response()?.errorBody()
            if (errorBody != null && code == 400 || code == 401 || code == 403){
                try {
                    val errorResponse = Gson().fromJson(errorBody?.string(), ErrorResponse::class.java)
                    Throwable(errorResponse.errorDescr)
                }catch (e:Throwable){
                    e.printStackTrace()
                    Throwable("unexpected error")
                }
            }else{
                error
            }
        }else if (exception is SocketTimeoutException){
            Throwable(context.getString(R.string.socket_timeout_exception))
        }else if (exception is UnknownServiceException){
            error
        }else{
            error
        }
    }

}