package com.example.loginmvvm01.domain.usecase

import com.example.loginmvvm01.domain.entities.UserDataModel
import com.example.loginmvvm01.domain.repo.UserDetailRepository
import kotlinx.coroutines.flow.Flow

class UserDetailUseCase(private val repository: UserDetailRepository) {
    suspend fun getUserList(): Flow<List<UserDataModel>> {
        return repository.getAllUsers()
    }

    suspend fun addUser(userDataModel: UserDataModel) = repository.addUser(userDataModel)

    suspend fun updateUser(userDataModel: UserDataModel) = repository.updateUserDetail(userDataModel)

    suspend fun deleteUser(userDataModel: UserDataModel) = repository.deleteUser(userDataModel)

    suspend fun getUserById(id : Long) = repository.getUserById(id)
}