package com.devbenadate.workout.models

import com.google.gson.annotations.SerializedName

data class LoginResponsse(
    var message:String,
    @SerializedName("access_token")var accesstoken: String,
    @SerializedName("user_id") var userId: String,
    @SerializedName("profile_id") var profileid: String
)
