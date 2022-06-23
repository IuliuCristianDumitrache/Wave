package com.example.wave.ui.users

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.wave.data.repository.UsersRepository
import com.example.wave.models.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val usersRepository: UsersRepository
) :
    ViewModel() {

    companion object {
        const val PAGE = 1
        const val LOAD_SIZE = 30
    }

    val usersList: MutableLiveData<PagingData<Results>> = MutableLiveData()

    fun getUsersList() {
        viewModelScope.launch {
            usersRepository.fetchUsersFlow(PAGE, LOAD_SIZE).cachedIn(
                viewModelScope
            ).distinctUntilChanged().collect {
                usersList.postValue(it)
            }
        }
    }
}