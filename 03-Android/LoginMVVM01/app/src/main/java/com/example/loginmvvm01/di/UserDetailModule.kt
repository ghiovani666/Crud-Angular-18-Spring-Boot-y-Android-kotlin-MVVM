package com.example.loginmvvm01.di

import com.example.loginmvvm01.MyApplication
import com.example.loginmvvm01.data.UserDetailRepositoryImpl
import com.example.loginmvvm01.data.db.UserDetailDao
import com.example.loginmvvm01.data.db.UserDetailDatabase
import com.example.loginmvvm01.domain.entities.UserViewModel
import com.example.loginmvvm01.domain.repo.UserDetailRepository
import com.example.loginmvvm01.domain.usecase.UserDetailUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
class UserDetailModule {

    @ViewModelScoped
    @Provides
    fun providesUserDetailUseCase(repository: UserDetailRepository) : UserDetailUseCase = UserDetailUseCase(repository)

    @ViewModelScoped
    @Provides
    fun providesUserDetailRepository() : UserDetailRepository {
        val db = UserDetailDatabase.getDatabase(MyApplication.instance)
        return  UserDetailRepositoryImpl(db.userDetail())
    }

    @ViewModelScoped
    @Provides
    fun providesUserViewModel(useCase: UserDetailUseCase): UserViewModel = UserViewModel(useCase)

}