package com.citrobyte.mpacker.mvvm.viewmodels

import androidx.lifecycle.*
import com.citrobyte.mpacker.mvvm.models.Result
import com.citrobyte.mpacker.repository.Repository
import com.citrobyte.mpacker.room.entities.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MainActivityViewModel @Inject constructor(private val repo:Repository) : ViewModel(){

    private val user = repo.getUser()
        .flowOn(Dispatchers.IO)
        .asLiveData(viewModelScope.coroutineContext)

    val getUser: LiveData<Result<User?>>
        get() = user

    private val userFlow = repo.getUserFlow()
        .flowOn(Dispatchers.IO)
        .asLiveData(viewModelScope.coroutineContext)

    val getUserFlow: LiveData<User?>
        get() = userFlow

    fun saveNewUser(){
        viewModelScope.launch(Dispatchers.IO){
            repo.saveNewUser()
        }
    }

    fun deleteAllUsers(){
        viewModelScope.launch(Dispatchers.IO){
            repo.deleteAllUsers()
        }
    }
}