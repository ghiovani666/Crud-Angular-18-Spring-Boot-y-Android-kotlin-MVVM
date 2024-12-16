package com.example.loginmvvm01.repository

import com.example.loginmvvm01.data.api.methods.UserApi
import com.example.loginmvvm01.data.api.request.LoginRequest
import com.example.loginmvvm01.data.api.response.LoginResponse
import retrofit2.Response

class UserRepository {

   suspend fun loginUser(loginRequest:LoginRequest): Response<LoginResponse>? {
      return  UserApi.getApi()?.loginUser(loginRequest = loginRequest)
    }
}