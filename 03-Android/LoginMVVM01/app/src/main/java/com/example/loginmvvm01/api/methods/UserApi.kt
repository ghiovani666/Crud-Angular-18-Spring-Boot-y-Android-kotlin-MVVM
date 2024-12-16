package com.example.loginmvvm01.data.api.methods

import com.example.loginmvvm01.data.api.ApiClient
import com.example.loginmvvm01.data.api.request.LoginRequest
import com.example.loginmvvm01.data.api.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("/auth/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<LoginResponse>

    companion object {
        fun getApi(): UserApi? {
            return ApiClient.client?.create(UserApi::class.java)
        }
    }
}