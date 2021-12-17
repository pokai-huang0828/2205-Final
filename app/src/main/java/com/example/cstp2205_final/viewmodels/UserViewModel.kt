package com.example.cstp2205_final.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cstp2205_final.model.entities.User
import com.example.cstp2205_final.model.repositories.UserRepository
import com.example.cstp2205_final.model.responses.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UserViewModel(val userRepository: UserRepository) : ViewModel() {
    val userStateFlow = MutableStateFlow<Resource<*>?>(null)

    init {
        viewModelScope.launch {
            userRepository.getUsers().collect {
                userStateFlow.value = it
            }
        }
    }

    fun getUserById(id: String, onResponse: (Resource<*>) -> Unit) = viewModelScope.launch {
        userRepository.getUserById(id, onResponse)
    }

    fun createUser(user: User, onResponse: (Resource<*>) -> Unit) = viewModelScope.launch {
        userRepository.createUser(user, onResponse)
    }

    fun updateUser(user: User, onResponse: (Resource<*>) -> Unit) = viewModelScope.launch {
        userRepository.updateUser(user, onResponse)
    }

    fun deleteUser(user: User, onResponse: (Resource<*>) -> Unit) = viewModelScope.launch {
        userRepository.deleteUser(user, onResponse)
    }
}

class UserViewModelFactory(private val userRepository: UserRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(userRepository) as T
        }
        throw IllegalStateException()
    }
}