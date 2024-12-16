package com.example.loginmvvm01.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.loginmvvm01.data.api.ApiClient
import com.example.loginmvvm01.data.api.methods.UserApi
import com.example.loginmvvm01.data.api.request.LoginRequest
import com.example.loginmvvm01.data.api.response.BaseResponse
import com.example.loginmvvm01.data.api.response.LoginResponse
import com.example.loginmvvm01.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    val userRepo = UserRepository()
    val loginResult: MutableLiveData<BaseResponse<LoginResponse>> = MutableLiveData()

    fun loginUser(email: String, pwd: String) {

        loginResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {

                val loginRequest = LoginRequest(
                    password = pwd,
                    email = email
                )
                val response = userRepo.loginUser(loginRequest = loginRequest)
                if (response?.code() == 200) {
                    loginResult.value = BaseResponse.Success(response.body())

                } else {
                    loginResult.value = BaseResponse.Error(response?.message())
                }

            } catch (ex: Exception) {
                loginResult.value = BaseResponse.Error(ex.message)
            }
        }
    }
}