package com.example.loginmvvm01.domain.repo

import com.example.loginmvvm01.domain.entities.UserDataModel
import kotlinx.coroutines.flow.Flow

interface UserDetailRepository {

    suspend fun addUser(userDataModel: UserDataModel)

    suspend fun getAllUsers(): Flow<List<UserDataModel>>

    suspend fun updateUserDetail(userDataModel: UserDataModel)

    suspend fun deleteUser(userDataModel: UserDataModel)

    suspend fun getUserById(id : Long)
}