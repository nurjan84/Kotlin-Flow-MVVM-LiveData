package com.citrobyte.mpacker.repository

import com.citrobyte.mpacker.mvvm.models.Result
import com.citrobyte.mpacker.room.dao.UserDao
import com.citrobyte.mpacker.room.entities.User
import com.citrobyte.mpacker.utils.ErrorsProcessor
import com.citrobyte.mpacker.utils.Logger
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject
import kotlin.random.Random

@ExperimentalCoroutinesApi
class Repository @Inject constructor(private val userDao: UserDao,  private val errors: ErrorsProcessor){

    fun getUser() =  flow {
        emit(Result.Loading(true))
        emit(Result.Success(userDao.getUser()))
    }.catch { e ->
        emit(Result.Error(errors.processError(e)))
    }.onCompletion {
        emit(Result.Loading(false))
    }

    fun getUserFlow() = userDao.getUserFlow()

    fun saveNewUser(){
        val user = User(0)
        user.firstName = Random.nextInt(0, 1000).toString()
        userDao.insert(user)
    }

    fun deleteAllUsers(){
        userDao.deleteAll()
    }

}