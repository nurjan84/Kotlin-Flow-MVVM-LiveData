package com.citrobyte.mpacker.mvvm.viewmodels

import androidx.lifecycle.*
import com.citrobyte.mpacker.mvvm.models.Result
import com.citrobyte.mpacker.repository.Repository
import com.citrobyte.mpacker.room.entities.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MainActivityViewModel @Inject constructor(repo:Repository) : ViewModel(){

    private val user = repo.getUser()
        .flowOn(Dispatchers.IO)
        .asLiveData(viewModelScope.coroutineContext)

    val getUser: LiveData<Result<User?>>
        get() = user
}