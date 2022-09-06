package com.devbenadate.workout

import com.devbenadate.workout.models.LoginRequest
import com.devbenadate.workout.models.LoginResponsse
import com.devbenadate.workout.models.register_response
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/register")
    fun registerUser(@Body registerRequest: RegisterRequest): Call<register_response>

    @POST("/login")
    fun loginUser(@Body LoginRequest: LoginRequest): Call<LoginResponsse>
}